package com.works.configs;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        String[] freeUrls = {"/", "/login"};
        boolean loginStatus = true;
        for( String item : freeUrls ) {
            if ( item.equals(url) ) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            boolean status = req.getSession().getAttribute("user") == null;
            if (status) {
                res.sendRedirect("/");
            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("Server DOWN");
    }
}
