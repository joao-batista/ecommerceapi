package br.com.ecommerceapi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Object expired = request.getAttribute("expired");
        if (expired != null){
            response.getWriter().write("{\"errorCode\": 401, \"message\": \"Unauthorized - invalid session\"}");
        }else{
            response.getWriter().write("{\"errorCode\": 401, \"message\": \"Unauthorized\"}");
        }
    }
}