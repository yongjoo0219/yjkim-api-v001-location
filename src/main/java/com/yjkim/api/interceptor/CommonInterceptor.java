package com.yjkim.api.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Common Interceptor.
 *
 * @author yjkim@ntels.com
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long trId = System.currentTimeMillis();
        request.setAttribute("TRID", trId);
        LOGGER.debug("#####REQUEST [{}, {}, {}, {}, trId={}]", request.getServletPath(), request.getQueryString(),
                request.getMethod(), request.getContentType(), trId);

        String servletPath = Optional.ofNullable(request.getServletPath()).orElse("");
        if ("/".equals(servletPath)) {
            return true;
        }

        if ("/yjkim/api/v1/location/search".equals(servletPath)) {
            //인메모리 DB 값 세팅.
        }
        return true;
    }
}
