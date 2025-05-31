package com.Health_Management.Filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();

        boolean isPublicRequest =
                uri.endsWith("/login") ||
                uri.endsWith("/login.html") ||
                uri.endsWith("/register") ||
                uri.endsWith("/register.html") ||
                uri.endsWith(".css") ||
                uri.endsWith(".js") ||
                uri.contains("/images") ||
                uri.equals(request.getContextPath() + "/");

        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("currentUser") != null);

        if (loggedIn || isPublicRequest) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
