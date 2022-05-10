package org.solowev.taskmanager.auth.exceptions;

import org.solowev.taskmanager.auth.exceptions.handler.ErrorCode;

/**
 * Exception for working with tokens
 */
public class TokenException extends TaskManagerException {
    /**
     * Default token exception code
     */
    private static final ErrorCode errorCode = ErrorCode.JWT_EXCEPTION;

    public TokenException() {
        super(errorCode);
    }

    public TokenException(String message) {
        super(errorCode, message);
    }
}
