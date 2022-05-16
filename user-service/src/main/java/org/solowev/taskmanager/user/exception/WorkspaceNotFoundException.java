package org.solowev.taskmanager.user.exception;

public class WorkspaceNotFoundException extends NotFoundException {
    /**
     * message to return
     */
    private static final String MESSAGE = "can't find workspace with id %d";

    public WorkspaceNotFoundException(Long workspaceId) {
        super(String.format(MESSAGE, workspaceId));
    }
}
