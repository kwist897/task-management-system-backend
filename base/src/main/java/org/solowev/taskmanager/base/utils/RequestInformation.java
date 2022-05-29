package org.solowev.taskmanager.base.utils;

import lombok.Data;

@Data
public class RequestInformation {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";

    private String correlationId;

    private String authToken;
}
