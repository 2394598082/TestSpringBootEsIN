import com.baizhi.dao.BookDao;
import com.baizhi.entity.Book;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestExport extends TestES {

    @Autowired
    private BookDao  bookDao;

    @Test
    public void extend(){
        Iterable<Book> all = bookDao.findAll();
        for (Book book : all) {
            System.out.println(book);

        }


    }


    public static void main(String[] args) throws Exception {
        //创建一个Excel表格
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个sheet
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //创建一个row (行)
        HSSFRow row = sheet.createRow(0);
        //创建一个cell (表格)
        HSSFCell cell = row.createCell(0);
        //在表格中填写内容
        cell.setCellValue("这是一个表格");
        //将excel对象写出到硬盘上
        workbook.write(new FileOutputStream("F://demo2.xls"));
    }
}
