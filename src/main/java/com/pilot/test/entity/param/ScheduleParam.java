package com.pilot.test.entity.param;


import com.pilot.test.entity.vo.ScheduleVo;
import lombok.Data;

@Data
public class ScheduleParam {

    private ScheduleVo[] schedules;
    private ReviewParam review;

}
