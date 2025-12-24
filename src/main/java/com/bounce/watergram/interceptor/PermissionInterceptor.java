package com.bounce.watergram.interceptor;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request
            , HttpServletResponse response
            , Object handler) throws IOException {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        String uri = request.getRequestURI();

        if (userId == null) {
            if (uri.startsWith("/post")) {
                response.sendRedirect("/user/login");

                return false;
            }

        } else {
            if (uri.startsWith("/user")) {

                response.sendRedirect("/post/timeline");

                return false;
            }
        }


        return true;
    }
}
