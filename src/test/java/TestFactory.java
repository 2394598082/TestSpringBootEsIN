import com.baizhi.factory.BeanFactoty;
import com.baizhi.factory.BeanFactotyImpl;

public class TestFactory {
    public static void main(String[] args) throws Exception {

//        Class<BeanFactotyImpl> clazz = BeanFactotyImpl.class;

        Class<?> clazz = Class.forName("com.baizhi.factory.BeanFactotyImpl");

        BeanFactoty beanFactoty = (BeanFactoty) clazz.newInstance();

        beanFactoty.show();


    }


}
