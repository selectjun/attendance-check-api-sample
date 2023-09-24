package com.attendancecheck.sample.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * UserContext 필터
 */
@Component
public class UserContextFilter implements Filter {

    /**
     * 로거
     */
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    /**
     * 초기화 시점 필터
     * @param filterConfig The configuration information associated with the filter instance being initialised
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * 사전 필터
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this filter to pass the request and response
     *                     to for further processing
     *
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

        logger.debug("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

        chain.doFilter(httpServletRequest, response);
    }

    /**
     * 종료 시점 필터
     */
    @Override
    public void destroy() {
    }

}
