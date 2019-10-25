package com.pilot.test.service.impl;

import com.pilot.test.entity.model.Review;
import com.pilot.test.entity.model.Schedule;
import com.pilot.test.entity.vo.ScheduleVo;
import com.pilot.test.entity.param.ReviewParam;
import com.pilot.test.entity.param.ScheduleParam;
import com.pilot.test.mapper.ReviewMapper;
import com.pilot.test.mapper.ScheduleMapper;
import com.pilot.test.service.IScheduleService;
import com.pilot.test.common.utils.CheckParamUtils;
import com.pilot.test.common.utils.StringTools;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private ReviewMapper reviewMapper;


    public static  List<ScheduleVo> scheduleList = new ArrayList<ScheduleVo>();

    /**
    将数据插入数据库
     */
    @Override
    @Transactional
    public String insert(ScheduleParam scheduleParam) throws Exception {
        List<Schedule> list = new ArrayList<Schedule>();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //获取日程数组
        ScheduleVo[] schedules = scheduleParam.getSchedules();
        //获取审批
        ReviewParam review = scheduleParam.getReview();
        Review app = new Review();
        String reviewtime = review.getReviewtime();
        String uuid = StringTools.getUUID();
        Date parseTime = df.parse(reviewtime);
        app.setId(uuid);
        app.setReviewtime(parseTime);
        app.setSuggestion(review.getSuggestion());
        app.setReviewperson(review.getReviewperson());

        String result = CheckParamUtils.checkParamArray(schedules, uuid, df, list);
        if(!"success".equals(result)){
            return  result;
        }
        Integer insert = reviewMapper.insert(app);
        if(insert>0){
            scheduleMapper.batchSave(list);
            return "success";
        }
        return "fail";
    }

    /**
      文件解析，验证参数
       */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public String batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        //验证上传文件类型
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return "文件类型不匹配";
        }

        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb;
        try {
            wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
        } finally {
            is.close();
        }


        //循环wb
        int numberOfSheets = wb.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {

            Sheet sheet = wb.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            if(sheet!=null){
                notNull = true;
            }
            ScheduleVo schedule;
            //循环每个sheet 从第一行开始
            for (int r = 1; r < sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null){
                    continue;
                }
                //验证参数
                schedule = new ScheduleVo();
                schedule.setId(StringTools.getUUID());
                String checkResult  = CheckParamUtils.checkParam(row, schedule);
                if(!"success".equals(checkResult)){
                    return  checkResult;
                }
                scheduleList.add(schedule);
            }
        }
        return "success";
    }

}
