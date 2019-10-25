package com.pilot.test.entity.vo;


import lombok.Data;


@Data
public class ScheduleVo {
    private String id;
    private String demandname;
    private String demandsources;
    private String rank;
    private String demandspecification;
    private String tasktype;
    private String version;
    private String person;
    private String planstarttime;
    private String planfinishtime;
    private Double planday;
    private String actualstarttime;
    private String actualfinishtime;
    private Double actualday;
    private String currentstatus;
    private String accepter;
    private String remark;
    private Long reviewid;
}
