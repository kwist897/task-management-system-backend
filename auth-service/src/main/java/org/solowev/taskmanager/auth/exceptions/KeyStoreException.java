package org.solowev.taskmanager.auth.exceptions;

import org.solowev.taskmanager.auth.exceptions.handler.ErrorCode;

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
