package com.park.config;

import com.park.service.BzAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BzAdminService bzAdminService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{

        auth.userDetailsService(bzAdminService).passwordEncoder(encoder());

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * authorizition 授权
         * 在shiro和SpringSecurity中 所有以Author开头的单词都和授权业务有关系
         *
         * authorizeRequests 配置拦截规则
         * antMatchers 配置路径
         * permitAll 不拦截
         */
        http.authorizeRequests()
//                配置不拦截
                .antMatchers("/admin/**","/img/**","/css/**","/js/**","/ztree/**","/login.jsp","/login","/layui/**","/bz/imgCode")
                .permitAll()
//                拦截所有 配置一般不会使用/** 而是独立配置
//                anyRequest 代表所有路径
                .anyRequest()
                .authenticated();
        /**
         * 自定义登录页面
         *
         * formLogin() 代表表单登录
         * loginPage 自定义登录页面
         * loginProcessingUrl 定义登录方法的地址 /login就是SpringSecurity中的认证方法
         * successForwardUrl 登录成功后的地址
         * failureForwardUrl 登录失败后的地址
         */
        http.formLogin()
                .loginPage("/login.jsp")
                //成功
                .successForwardUrl("/bz/successForwardUrl")
                //失败
                .failureForwardUrl("/bz/failureForwardUrl")

                .loginProcessingUrl("/login")
                .and()
                .csrf()
                .disable()
        ;

//       html iframe标签引用二级页面 会被默认拦截
//        可以配置不拦截
        http.headers().frameOptions().disable();
    }

}
