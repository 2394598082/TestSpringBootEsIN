package com.baizhi.dao.impl;

import com.baizhi.dao.CustomerBookRepository;
import com.baizhi.entity.Book;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerBookRepositoryImpl implements CustomerBookRepository {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public List<Book> select(int page, int limit, String c) {
        //查询策略
        TermQueryBuilder termQuery = QueryBuilders.termQuery("name", c);
        //高亮
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        //综合查询方案
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQuery)
                .withHighlightFields(field)
                .withPageable(PageRequest.of(page - 1, limit))
                .build();

        //queryForPage 方法被执行后,自动调用SearchResultMapper接口对象的mapResults方法
        //在方法中进一步封装查询结果
        AggregatedPage<Book> books = elasticsearchTemplate.queryForPage(searchQuery, Book.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                SearchHits hits = response.getHits();
                List<Book> list = new ArrayList<>();
                for (SearchHit hit : hits) {
                    Book book = new Book();
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    HighlightField name = hit.getHighlightFields().get("name");
                    if(name!=null){
                        sourceAsMap.put("name", name.fragments()[0].toString());
                    }
                    book.setName(sourceAsMap.get("name").toString());

                    book.setAuthor(hit.getSourceAsMap().get("author").toString());

                    book.setPrice(Double.parseDouble(hit.getSourceAsMap().get("price").toString()));
                    list.add(book);


                }


                return new AggregatedPageImpl<T>((List<T>) list);

            }
        });


        return books.getContent();
    }
}
