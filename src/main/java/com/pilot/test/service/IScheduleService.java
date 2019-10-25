package com.pilot.test.service;

import com.pilot.test.entity.param.ScheduleParam;
import org.springframework.web.multipart.MultipartFile;


public interface IScheduleService {
    String insert(ScheduleParam scheduleParam) throws Exception;

    String batchImport(String fileName, MultipartFile file) throws Exception;
}
