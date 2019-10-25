package com.pilot.test.entity.model;

import lombok.Data;

import java.util.Date;

@Data
public class Schedule {
    private String id;
    private String demandname;
    private String demandsources;
    private Integer rank;
    private String demandspecification;
    private Integer tasktype;
    private String version;
    private String person;
    private Date planstarttime;
    private Date planfinishtime;
    private Double planday;
    private Date actualstarttime;
    private Date actualfinishtime;
    private Double actualday;
    private Integer currentstatus;
    private String accepter;
    private String remark;
    private String reviewid;
}
