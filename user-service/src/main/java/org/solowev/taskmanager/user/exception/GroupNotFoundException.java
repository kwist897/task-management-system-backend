package org.solowev.taskmanager.user.exception;

public class GroupNotFoundException extends NotFoundException {
    /**
     * message to return
     */
    private static final String MESSAGE = "can't find group with id %d";

    public GroupNotFoundException(Long groupId) {
        super(String.format(MESSAGE, groupId));
    }
}
