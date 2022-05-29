package org.solowev.taskmanager.base.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        RequestInformationHolder.getRequestInformation().setCorrelationId(httpServletRequest.getHeader(RequestInformation.CORRELATION_ID));
        RequestInformationHolder.getRequestInformation().setAuthToken(httpServletRequest.getHeader(RequestInformation.AUTH_TOKEN));

        log.debug("correlation id is {}", RequestInformationHolder.getRequestInformation().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
