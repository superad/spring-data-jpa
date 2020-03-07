package com.lagou.edu.component.filter;

import com.lagou.edu.controller.LoginController;
import com.lagou.edu.util.CookieUtils;
import com.lagou.edu.util.MD5Utils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fgm
 * @description  登录拦截器
 * @date 2020-03-07
 ***/
public class LoginFilter implements Filter {

    private List<String> excludeUrls=new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       String excludes= filterConfig.getInitParameter("excludes");
       if(StringUtils.isEmpty(excludes)){
           return;
       }
       for(String url:excludes.split(",")){
           excludeUrls.add(url);
       }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if(ignore(httpServletRequest)){
            chain.doFilter(request,response);
            return;
        }
        String cookieValue=CookieUtils.getCookieByKey(LoginController.USER_COOKIE,httpServletRequest);
        String token= MD5Utils.toMD5("admin_admin");
        if(!StringUtils.isEmpty(cookieValue)&&cookieValue.equals(token)){
            chain.doFilter(request,response);
        }else{
            request.getRequestDispatcher("/index.html").forward(request,response);
        }

    }

    private boolean ignore(HttpServletRequest request) {
        String uri=request.getRequestURI();
        for(String excludeUrl:excludeUrls){
            //后缀匹配
            if(excludeUrl.startsWith("*.")&& uri.endsWith(excludeUrl.substring(1))){
               return true;
            }
            //前缀匹配
            if(!excludeUrl.startsWith("/")) {
                // 前缀匹配，必须要是/开头
                excludeUrl = "/" + excludeUrl;
            }
            if(excludeUrl.endsWith("/*")){
                String prefixUrl = request.getContextPath() + excludeUrl.substring(0, excludeUrl.length() - 1);
                if(uri.startsWith(prefixUrl)){
                    return true;
                }
            }
            //全路径匹配
            String targetUrl = request.getContextPath() + excludeUrl;
            if(uri.equals(targetUrl)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public void destroy() {

    }
}
