package org.solowev.taskmanager.base.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDto {
    private Date dateCreated;
    private Date dateUpdated;
}
