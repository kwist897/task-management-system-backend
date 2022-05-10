package org.solowev.taskmanager.auth.exceptions;

import org.solowev.taskmanager.auth.exceptions.handler.ErrorCode;

public class NotFoundException extends TaskManagerException {
    /**
     * Default error code
     */
    private static final ErrorCode errorCode = ErrorCode.NOT_FOUND_EXCEPTION;

    public NotFoundException(String message) {
        super(errorCode, message);
    }

    public NotFoundException() {
        super(errorCode);
    }

}
