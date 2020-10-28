import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TestUser extends TestES{
    @Autowired
    UserDao userDao;
    @Test
    public void select(){
        List<User> users = userDao.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
