package com.example.websocketconfig;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by tooji on 3/5/2017.
 */
public class HttpSessionIdHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            if (request == null) System.out.print("request is null!");

            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            System.out.println(request.getRemoteAddress().getHostName());

            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                System.out.println("Session has id "+session.getId());
                attributes.put("HTTPSESSIONID", session.getId());
            }else{
                System.out.println("session is NULL!");
            }
        }
        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
    }
}