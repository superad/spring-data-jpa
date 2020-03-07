package com.lagou.edu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fgm
 * @description  cookie util
 * @date 2020-03-07
 ***/
public class CookieUtils {

    private static final Integer DEFAULT_LIFECYCLE=60;

    /**
     * 添加Cookie
     * @param key
     * @param value
     * @param response
     */
    public static void addCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        cookie.setMaxAge(DEFAULT_LIFECYCLE);
        response.addCookie(cookie);
    }

    /**
     * 失效Cookie
     * @param key
     * @param request
     * @param response
     */
    public static void removeCookie(String key, HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies=request.getCookies();
        if(null==cookies||cookies.length==0){
            return;
        }
        for(Cookie cookie:cookies){
           if(key.equals( cookie.getName())){
               cookie.setMaxAge(0);
               cookie.setPath("/");
               response.addCookie(cookie);
           }
        }
    }

    /**
     * 通过key获取cookie
     * @param key
     * @param request
     * @return
     */
    public static String getCookieByKey(String key,HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(null==cookies||cookies.length==0){
            return null;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }



}
