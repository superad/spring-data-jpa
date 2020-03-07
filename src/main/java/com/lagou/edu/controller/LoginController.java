package com.lagou.edu.controller;

import com.lagou.edu.pojo.LoginInfo;
import com.lagou.edu.util.CookieUtils;
import com.lagou.edu.util.MD5Utils;
import com.lagou.edu.vo.Result;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fgm
 * @description
 * @date 2020-03-07
 ***/
@Controller
public class LoginController {


    public static String USER_COOKIE="userCookie";


    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody LoginInfo loginInfo,
                        HttpServletRequest request, HttpServletResponse response) throws IOException {

        if("admin".equals(loginInfo.getUserName())&&"admin".equals(loginInfo.getPassword())){
            String token= MD5Utils.toMD5(loginInfo.getUserName()+"_"+loginInfo.getPassword());
            CookieUtils.addCookie(USER_COOKIE,token,response);
            System.out.println("登录成功");
            return Result.success();
        }
        return Result.failed("403","错误的用户名或密码!");

    }

    @GetMapping("/logout")
    @ResponseBody
    public Result logout(@Param("userName") String userName,HttpServletRequest request, HttpServletResponse response){
        CookieUtils.removeCookie(USER_COOKIE,request,response);
        return Result.success();
    }


}
