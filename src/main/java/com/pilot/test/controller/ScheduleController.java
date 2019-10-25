package com.pilot.test.controller;


import com.pilot.test.common.config.ModelView;
import com.pilot.test.common.exception.a.BaseApiService;
import com.pilot.test.common.exception.a.ResponseBase;
import com.pilot.test.entity.param.ScheduleParam;
import com.pilot.test.entity.vo.ScheduleVo;
import com.pilot.test.service.IScheduleService;
import com.pilot.test.service.impl.ScheduleServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ScheduleController extends BaseApiService {


    @Autowired
    private IScheduleService scheduleService;

    @ModelView
    @Transactional
    @ApiOperation(value = "文件导入", notes = "文件导入")
    @PostMapping("/fileUpload")
    public ResponseBase fileUpload(@RequestParam(value = "file", required = true) MultipartFile file) {
        String result;
        String fileName = file.getOriginalFilename();
        try {
            ScheduleServiceImpl.scheduleList = new ArrayList<ScheduleVo>();
            result = scheduleService.batchImport(fileName, file);
            log.info("result:", result);
            if ("success".equals(result)) {
                List<ScheduleVo> scheduleList = ScheduleServiceImpl.scheduleList;
                log.info("scheduleList:", scheduleList);
                return setResultSuccessWithData(scheduleList);
            } else {
                return setResultError(500, result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @ApiOperation(value = "添加信息", notes = "添加信息")
    @ApiImplicitParam(name = "scheduleParam", value = "实体", required = true, dataType = "ScheduleParam")
    @PostMapping("/save")
    public ResponseBase add(@RequestBody ScheduleParam scheduleParam) {
        try {
            log.info("scheduleParam:", scheduleParam);
            String result = scheduleService.insert(scheduleParam);
            if (result.equals("success")) {
                return setResultSuccess();
            }
            return setResultError(500, result);
        } catch (Exception e) {
            e.printStackTrace();
            return setResultError(500, "出错了");
        }
    }

}
