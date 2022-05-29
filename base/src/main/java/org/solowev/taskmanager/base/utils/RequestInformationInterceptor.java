package org.solowev.taskmanager.base.utils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class RequestInformationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header(RequestInformation.CORRELATION_ID, RequestInformationHolder.getRequestInformation().getCorrelationId());
        requestTemplate.header(RequestInformation.AUTH_TOKEN, RequestInformationHolder.getRequestInformation().getAuthToken());
    }
}
