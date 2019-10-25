package com.pilot.test.mapper;


import com.pilot.test.entity.model.Schedule;

import java.util.List;

public interface ScheduleMapper {

    void batchSave(List<Schedule> listSchedule);

}
