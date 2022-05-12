package org.solowev.taskmanager.base.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_EXCEPTION("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCESS_DENIED_EXCEPTION("Not enough rights to view", HttpStatus.FORBIDDEN),
    NOT_FOUND_EXCEPTION("Couldn't find resource", HttpStatus.NOT_FOUND),
    JWT_EXCEPTION("Error occurred while processing token", HttpStatus.INTERNAL_SERVER_ERROR),
    RESOURCE_EXISTS_EXCEPTION("Resource already exists", HttpStatus.CONFLICT),
    KEYSTORE_EXCEPTION("Error occurred while processing keystore", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST);

    private final String message;

    private final HttpStatus httpStatus;
}
