package com.gzu.community_cloud.residence;

import com.gzu.community_cloud.residence.feign.AuthenticationFeign;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/residence/*"})
public class ResidenceFilter implements Filter {
    AuthenticationFeign authenticationFeign;

    public ResidenceFilter(AuthenticationFeign authenticationFeign) {
        this.authenticationFeign = authenticationFeign;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String username = authenticationFeign.getUsername(req.getHeader("Cookie"));
        if (username == null) {
            resp.sendRedirect("/");
            return;
        } else {
            req.setAttribute("username", username);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
