package com.railway.manager.filter;


import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

@WebFilter(urlPatterns = "*")
@Component
public class CORSFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) {

    }

    private static final List<String> DISABLE_URI_LIST = Arrays.asList("/hystrix.stream", "/healthCheck/disable");

    private static final List<String> ALLOW_IP_LIST = Arrays.asList("127.0.0.1", "localhost","0:0:0:0:0:0:0:1","[::1]","::1",InetAddress.getLoopbackAddress().getHostAddress());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI();
        String address = request.getRemoteAddr();
        if (DISABLE_URI_LIST.contains(path) && !ALLOW_IP_LIST.contains(address)) {
            response.setStatus(403);
            return;
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-control-allow-credentials", "true");
        response.setHeader("Timing-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "x-request-id, token, Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        //设置cache-control:must-revalidate
        response.setHeader("Cache-Control","private,must-revalidate");
        filterChain.doFilter(servletRequest, response);
    }

    @Override
    public void destroy() {

    }
}
