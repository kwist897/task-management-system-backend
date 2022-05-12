package org.solowev.taskmanager.auth.exceptions;

import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;

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
