package com.baizhi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "es2",type = "book")
@Data
public class Book {
    @Id
    private Long id;
    @Field(type = FieldType.Text,analyzer ="ik_max_word")
    private String name;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Double)
    private Double price;

}
