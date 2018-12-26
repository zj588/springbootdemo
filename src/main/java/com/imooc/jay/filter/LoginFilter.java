package com.imooc.jay.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器
 *
 * 1、@Component 这个注解的目的是将LoginFilter交给容器来处理。也就是让LoginFilter起作用
 *
 * 2、@ServletComponentScan 这个使用来扫描@WebFilter 的让@WebFilter起作用。当然对于servlet线管注解也是可以的。这个@ServletComponentScan最好写在Apllication这个上面，通用配置。我这里因为只有一个Filter所以没有写在Application上面。
 *
 * 3、@WebFilter 这个用处显而易见，针对于什么链接做过滤，filter的名称是为什么。
 */
//@Component
//@ServletComponentScan
//@WebFilter(filterName = "loginFilter", urlPatterns = "/area/*")
//public class LoginFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        if (request.getPathInfo() != null && request.getPathInfo().startsWith("/noAuth")) {
//            filterChain.doFilter(servletRequest, response);
//            return;
//        }
//
//        String userTokenStr = request.getHeader("Authorization");
//
//        if (userTokenStr == null) {
//            userTokenStr = ServletRequestUtils.getStringParameter(request, "t_token");
//        }
//        if (userTokenStr == null) {
//            userTokenStr = request.getParameter("Authorization");
//        }
//
//
//
//        if (StringUtils.isEmpty(userTokenStr)) {
//            response.getWriter().print("{\"code\":10000, \"message\": \"t_token parameter is required\", \"data\": {}}");
//            response.getWriter().flush();
//            return;
//        }
//        try {
//            // todo: 验证登录是否有效
//
//        } catch (Exception exception){
//            //Invalid signature/claims
//            response.getWriter().print("{\"code\":9999, \"message\": \"teacher token validate failed\", \"data\": {}}");
//            response.getWriter().flush();
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
