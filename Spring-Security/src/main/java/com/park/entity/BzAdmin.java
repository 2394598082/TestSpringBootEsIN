package com.park.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@TableName("bz_admin")
@Data
@Accessors(chain =true)
public class BzAdmin implements UserDetails {
    private int id;
    private String username;
    private String password;
   /*
   * @TableLogic 逻辑删除的注解,如果没有value delval的配置
   * 会采用全局配置
   * 0 代表正常 value
   * 1 代表删除 delval
   * */
    @TableLogic(value = "0",delval = "1")
    private int status = 0;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
