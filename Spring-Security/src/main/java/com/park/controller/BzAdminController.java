package com.park.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.park.dao.BzAdminMapper;
import com.park.entity.BzAdmin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Slf4j
@RequestMapping("bz")
@Controller
public class BzAdminController {
    @Autowired
    private BzAdminMapper bzAdminMapper;

    //登录
    @RequestMapping("successForwardUrl")
    public String adminLogin1(BzAdmin bzAdmin) {
        return "redirect:/main.jsp";
    }

    @RequestMapping("failureForwardUrl")
    @ResponseBody
    public String adminLogin2(BzAdmin bzAdmin) {
        return "error";
    }

    @RequestMapping("imgCode")
    public void imgCode(HttpServletResponse response,HttpSession session){
        //产生验证码图片的。图片的宽是116，高是36，验证码的长度是4，干扰线的条数是20
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 4, 20);
        //获取验证码图片中的字符串
        String code1 = lineCaptcha.getCode();

        ServletOutputStream outputStream=null;
        try {
            //获取到response的响应流。
            outputStream = response.getOutputStream();
            //把图片放入到response的相应流中。
            lineCaptcha.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("oldCode",code1);
    }

}
