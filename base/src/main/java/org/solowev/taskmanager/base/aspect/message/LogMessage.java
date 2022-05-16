package org.solowev.taskmanager.base.aspect.message;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class LogMessage {
    private String controller;
    private String method;
    private LogDirection direction;
    private String username;

    public String getMessage(){
        return new StringBuilder()
                .append(controller)
                .append("::")
                .append(method)
                .append(" direction: ")
                .append(direction)
                .append(" user: ")
                .append(username)
                .append(" time: ")
                .append(new Date())
                .toString();
    }
}