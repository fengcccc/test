package com.pilot.test.entity.model;

import lombok.Data;

import java.util.Date;

@Data
public class Review {
    private String id;
    private String reviewperson;
    private Date reviewtime;
    private String suggestion;
}
