package com.pilot.test.controller;

import com.pilot.test.common.exception.AjaxResponse;
import com.pilot.test.service.impl.ExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestExceptionController {
    @Autowired
    ExceptionService exceptionService;

    @RequestMapping("/system")
    public AjaxResponse system() {
        exceptionService.systemBizError();
        return AjaxResponse.success();
    }

    @RequestMapping("/user")
    public AjaxResponse user(Integer input) {
        return AjaxResponse.success(exceptionService.userBizError(input));
    }

}
