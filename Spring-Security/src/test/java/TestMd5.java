import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class TestMd5 {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password="123456";
        String encode = encoder.encode(password);
        System.out.println(encode);
        System.out.println(encoder.matches("123456","$2a$10$6oCdVOzebypoI5liQX6gXOYS6NSeeuAUabcUlt4LvrY7lG/j0nOjO"));
        System.out.println(encoder.matches("123456","$2a$10$OP1JYTE6PjYEfkkc7UEBD.YJDNOrtNk42oXqH/rTeShlmONRmSjwO"));
        System.out.println(encoder.matches("123456","$2a$10$jBm9OLa/1llh1Dv38ZoYGOxkbaBr3BZakeR8L73K/kXNaHn4QHzn."));












    /*    //明文
        String s = DigestUtils.md5Hex("123456");
        //暗文
        System.out.println(s);
        String substring = UUID.randomUUID().toString().substring(0, 4);
        //加盐
        String s1 = DigestUtils.md5Hex(s + substring);
        System.out.println(s1);
        //散列
        for (int i = 0; i < 1024; i++) {
            s1 = DigestUtils.md5Hex(s1);
        }
        System.out.println(s1);*/
    }
}
