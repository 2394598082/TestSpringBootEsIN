import com.park.Application;
import com.park.dao.BzAdminMapper;
import com.park.entity.BzAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestBz {
    @Autowired
    private BzAdminMapper bzAdminMapper;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Test
    public void se(){
        List<BzAdmin> bzAdmins = bzAdminMapper.selectList(null);
        for (BzAdmin bzAdmin : bzAdmins) {

            System.out.println(bzAdmin);
        }
    }
    @Test
    public void ses(){

        String encode = encoder.encode("123456");
        BzAdmin s = new BzAdmin().setPassword(encode).setUsername("longwu");
        bzAdminMapper.insert(s);

    }

}
