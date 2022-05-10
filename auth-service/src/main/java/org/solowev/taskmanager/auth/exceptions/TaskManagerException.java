package org.solowev.taskmanager.auth.exceptions;

import lombok.Getter;
import org.solowev.taskmanager.auth.exceptions.handler.ErrorCode;

/**
 * Common exception
 */
@Getter
public class TaskManagerException extends RuntimeException {

    private final ErrorCode errorCode;

    public TaskManagerException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public TaskManagerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
