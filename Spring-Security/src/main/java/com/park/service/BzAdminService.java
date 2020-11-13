package com.park.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.park.dao.BzAdminMapper;
import com.park.entity.BzAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class BzAdminService extends ServiceImpl<BzAdminMapper,BzAdmin> implements UserDetailsService {
    @Autowired
    private HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String code = request.getParameter("code");
        String oldCode =(String) request.getSession().getAttribute("oldCode");
       
        if (!oldCode.equalsIgnoreCase(code)){
            throw  new UsernameNotFoundException("验证码错误");
        }
        BzAdmin username = getOne(new QueryWrapper<BzAdmin>()
                .eq("username", s));

        if (username==null){
            throw  new UsernameNotFoundException("用户不存在");
        }
        return username;
    }
}
