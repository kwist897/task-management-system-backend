package org.solowev.taskmanager.user.exception;

public class ProfileNotFoundException extends NotFoundException {
    /**
     * message to return
     */
    private static final String MESSAGE = "can't find profile with id %d";

    public ProfileNotFoundException(Long profileId) {
        super(String.format(MESSAGE, profileId));
    }

    public ProfileNotFoundException(){
        super("couldn't find profile for current user");
    }
}
