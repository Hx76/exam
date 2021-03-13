package com.onlineexam.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import java.io.IOException;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, javax.servlet.FilterChain filterChain) throws ServletException, IOException, ServletException {
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, X-Access-Token");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
