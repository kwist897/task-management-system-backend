package org.solowev.taskmanager.auth.exceptions;

import org.solowev.taskmanager.base.exceptions.ErrorCode;
import org.solowev.taskmanager.base.exceptions.TaskManagerException;

/**
 * Exception for working with keystore
 */
public class KeyStoreException extends TaskManagerException {

    private static final ErrorCode errorCode = ErrorCode.KEYSTORE_EXCEPTION;

    public KeyStoreException(String message) {
        super(errorCode, message);
    }

    public KeyStoreException() {
        super(errorCode);
    }
}
