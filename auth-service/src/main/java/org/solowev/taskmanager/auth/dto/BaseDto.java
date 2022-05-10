package org.solowev.taskmanager.auth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {
    private Date dateCreated;
    private Date dateUpdated;
}
