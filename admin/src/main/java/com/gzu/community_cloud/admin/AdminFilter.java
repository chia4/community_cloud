package com.gzu.community_cloud.admin;

import com.gzu.community_cloud.admin.feign.AuthenticationFeign;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/admin/*"})
public class AdminFilter implements Filter {
    AuthenticationFeign authenticationFeign;

    public AdminFilter(AuthenticationFeign authenticationFeign) {
        this.authenticationFeign = authenticationFeign;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String isAdmin = authenticationFeign.isAdmin(req.getHeader("Cookie"));
        if (!isAdmin.equals("1")) {
            resp.sendRedirect("/");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}