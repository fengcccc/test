package com.pilot.test.common.utils;


import com.alibaba.excel.util.StringUtils;
import com.pilot.test.entity.model.Schedule;
import com.pilot.test.entity.vo.ScheduleVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CheckParamUtils {

    public static String checkParamArray(ScheduleVo[] schedules, String uuid, SimpleDateFormat df, List<Schedule> list) throws Exception {

        //验证schedules
        Schedule schedule;
        for (int r = 0; r < schedules.length; r++) {
            schedule = new Schedule();
            schedule.setId(StringTools.getUUID());
            schedule.setReviewid(uuid);
            String demandName = schedules[r].getDemandname();
            if (StringUtils.isEmpty(demandName)) {
                return "需求名称不能为空";
            }
            if (!StringUtils.isEmpty(demandName)) {
                schedule.setDemandname(demandName);
            }

            String demandSources = schedules[r].getDemandsources();
            if (StringUtils.isEmpty(demandSources)) {
                return "需求来源不能为空";
            }
            if (!StringUtils.isEmpty(demandSources)) {
                schedule.setDemandsources(demandSources);
            }


            String rankStr = schedules[r].getRank();
            if (StringUtils.isEmpty(rankStr)) {
                return "优先级不能为空";
            }
            if (!StringUtils.isEmpty(rankStr)) {
                if (!("高".equals(rankStr) || "中".equals(rankStr) || "低".equals(rankStr))) {
                    return "优先级填写有误";
                }
                Integer rank = 1;
                if ("高".equals(rankStr)) {
                    rank = 1;
                }
                if ("中".equals(rankStr)) {
                    rank = 2;
                }
                if ("低".equals(rankStr)) {
                    rank = 3;
                }
                schedule.setRank(rank);
            }

            String demandSpecification = schedules[r].getDemandspecification();
            if (StringUtils.isEmpty(demandSpecification)) {
                return "需求说明不能为空";
            }
            if (!StringUtils.isEmpty(demandSpecification)) {
                schedule.setDemandspecification(demandSpecification);
            }


            String taskTypeStr = schedules[r].getTasktype();

            if (StringUtils.isEmpty(taskTypeStr)) {
                return "任务类型不能为空";
            }
            if (!StringUtils.isEmpty(taskTypeStr)) {
                if (!("bug对应".equals(taskTypeStr) || "项目定制开发".equals(taskTypeStr) || "产品设计".equals(taskTypeStr))) {
                    return "任务类型填写有误";
                }
                Integer taskType = 1;
                if ("bug对应".equals(taskTypeStr)) {
                    taskType = 1;
                }
                if ("项目定制开发".equals(taskTypeStr)) {
                    taskType = 2;
                }
                if ("产品设计".equals(taskTypeStr)) {
                    taskType = 3;
                }
                schedule.setTasktype(taskType);
            }

            String version = schedules[r].getVersion();
            if (StringUtils.isEmpty(version)) {
                return "版本Ver.不能为空";
            }
            if (!StringUtils.isEmpty(version)) {
                schedule.setVersion(version);
            }


            String person = schedules[r].getPerson();
            if (StringUtils.isEmpty(person)) {
                return "责任人不能为空";
            }
            if (!StringUtils.isEmpty(person)) {
                schedule.setPerson(person);
            }


            String planStartTimeStr = schedules[r].getPlanstarttime();
            String planFinishTimeStr = schedules[r].getPlanfinishtime();

            if (StringUtils.isEmpty(planStartTimeStr)) {
                return "计划起始时间不能为空";
            }

            if (StringUtils.isEmpty(planFinishTimeStr)) {
                return "计划结束时间不能为空";
            }
            if (!StringUtils.isEmpty(planStartTimeStr) && !StringUtils.isEmpty(planFinishTimeStr)) {

                long difTime = StringTools.difTime(planStartTimeStr, planFinishTimeStr);
                if (difTime > 6) {
                    return "计划起始时间应该小于5天";
                }
                Date sTime = df.parse(planStartTimeStr);
                Date endTime = df.parse(planFinishTimeStr);

                schedule.setPlanstarttime(sTime);
                schedule.setPlanfinishtime(endTime);
            }

            Double planDay = schedules[r].getPlanday();
            if (planDay == null) {
                return "计划人日不能为空";
            }
            if (planDay != null) {
                schedule.setPlanday(planDay);
            }

            String actualStartTimeStr = schedules[r].getActualstarttime();
            if (StringUtils.isEmpty(actualStartTimeStr)) {
                return "实际起始时间不能为空";
            }
            if (!StringUtils.isEmpty(actualStartTimeStr)) {


                Date actualStartTime = df.parse(actualStartTimeStr);

                schedule.setActualstarttime(actualStartTime);
            }

            String actualFinishTimeStr = schedules[r].getActualfinishtime();
            if (StringUtils.isEmpty(actualFinishTimeStr)) {
                return "实际结束时间不能为空";
            }
            if (!StringUtils.isEmpty(actualFinishTimeStr)) {


                Date actualEndTime = df.parse(actualFinishTimeStr);

                //实际结束时间 >计划结束时
                int result = actualFinishTimeStr.compareTo(actualFinishTimeStr);
                if (result > 0) {//此时备注栏不能为空
                    String remark = schedules[r].getRemark();
                    if (StringUtils.isEmpty(remark)) {
                        return "请说明延迟理由";
                    }
                    if (!StringUtils.isEmpty(remark)) {
                        schedule.setRemark(remark);
                    }
                }

                schedule.setActualfinishtime(actualEndTime);
            }


            Double actualDay = schedules[r].getActualday();
            if (actualDay == null) {
                return "实际人日不能为空";
            }
            if (actualDay != null) {
                schedule.setActualday(actualDay);
            }

            String currentStatusStr = schedules[r].getCurrentstatus();
            String accepter = schedules[r].getAccepter();
            if (StringUtils.isEmpty(currentStatusStr)) {
                return "当前状态不能为空";
            }
            if (!StringUtils.isEmpty(currentStatusStr)) {
                if (!("已完成".equals(currentStatusStr) || "未开始".equals(currentStatusStr) || "正在进行".equals(currentStatusStr) || "暂停".equals(currentStatusStr) || "待定".equals(currentStatusStr) || "待测试".equals(currentStatusStr))) {
                    return "当前状态填写有误";
                }
                Integer currentStatus = 1;
                if ("已完成".equals(currentStatusStr)) {
                    currentStatus = 1;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        if (person.equals(accepter)) {
                            return "验收人不能和责任人一致";
                        }
                        schedule.setAccepter(accepter);
                    }
                }
                if ("未开始".equals(currentStatusStr)) {
                    currentStatus = 2;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        schedule.setAccepter(accepter);
                    }
                }
                if ("正在进行".equals(currentStatusStr)) {
                    currentStatus = 3;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        schedule.setAccepter(accepter);
                    }
                }
                if ("暂停".equals(currentStatusStr)) {
                    currentStatus = 4;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        schedule.setAccepter(accepter);
                    }
                }
                if ("待定".equals(currentStatusStr)) {
                    currentStatus = 5;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        schedule.setAccepter(accepter);
                    }
                }
                if ("待测试".equals(currentStatusStr)) {
                    currentStatus = 6;
                    if (StringUtils.isEmpty(accepter)) {
                        return "验收人不能为空";
                    }
                    if (!StringUtils.isEmpty(accepter)) {
                        schedule.setAccepter(accepter);
                    }
                }
                schedule.setCurrentstatus(currentStatus);
            }
            list.add(schedule);
        }
        return "success";
    }

    public static String checkParam(Row row, ScheduleVo schedule) {
        if (row.getCell(0) == null) {
            return "需求名称不能为空";
        }
        if (row.getCell(0) != null) {
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            schedule.setDemandname(row.getCell(0).getStringCellValue());
        }
        if (row.getCell(1) == null) {
            return "需求来源不能为空";
        }
        if (row.getCell(1) != null) {
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            schedule.setDemandsources(row.getCell(1).getStringCellValue());
        }
        if (row.getCell(2) == null) {
            return "优先级不能为空";
        }
        if (row.getCell(2) != null) {
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            String rankStr = row.getCell(2).getStringCellValue();
            if (!(rankStr.equals("高") || rankStr.equals("中") || rankStr.equals("低"))) {
                return "优先级填写有误";
            }
            schedule.setRank(rankStr);
        }
        if (row.getCell(3) == null) {
            return "需求说明不能为空";
        }
        if (row.getCell(3) != null) {
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            schedule.setDemandspecification(row.getCell(3).getStringCellValue());
        }
        if (row.getCell(4) == null) {
            return "任务类型不能为空";
        }
        if (row.getCell(4) != null) {
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            String tasktypeStr = row.getCell(4).getStringCellValue();
            if (!("bug对应".equals(tasktypeStr) || "项目定制开发".equals(tasktypeStr) || "产品设计".equals(tasktypeStr))) {
                return "任务类型填写有误";
            }
            schedule.setTasktype(tasktypeStr);
        }
        if (row.getCell(5) == null) {
            return "版本Ver.不能为空";
        }
        if (row.getCell(5) != null) {
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            schedule.setVersion(row.getCell(5).getStringCellValue());
        }

        if (row.getCell(6) == null) {
            return "责任人不能为空";
        }
        String person = null;
        if (row.getCell(6) != null) {
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            person = row.getCell(6).getStringCellValue();
            schedule.setPerson(person);
        }

        if (row.getCell(7) == null) {
            return "计划起始时间不能为空";
        }
        if (row.getCell(7) != null && row.getCell(8) != null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date sdate = row.getCell(7).getDateCellValue();

            String planStartTimeStr = df.format(sdate);

            if (row.getCell(8) == null) {
                return "计划结束时间不能为空";
            }
            Date eDate = row.getCell(8).getDateCellValue();
            String planFinishTimeStr = df.format(eDate);

            long difTime = StringTools.difTime(planStartTimeStr, planFinishTimeStr);
            if (difTime > 6) {
                return "计划起始时间应该小于5天";
            }

            schedule.setPlanstarttime(planStartTimeStr);
            schedule.setPlanfinishtime(planFinishTimeStr);
        }

        if (row.getCell(9) == null) {
            return "计划人日不能为空";
        }
        if (row.getCell(9) != null) {
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            String stringCellValue = row.getCell(9).getStringCellValue();
            schedule.setPlanday(Double.parseDouble(stringCellValue));
        }
        if (row.getCell(10) == null) {
            return "实际起始时间不能为空";
        }
        if (row.getCell(10) != null) {

            Date sdate = row.getCell(10).getDateCellValue();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String sdateStr = df.format(sdate);
            schedule.setActualstarttime(sdateStr);
        }

        if (row.getCell(11) == null) {
            return "实际结束时间不能为空";
        }
        if (row.getCell(11) != null) {

            Date edate = row.getCell(11).getDateCellValue();

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String actualfinishtimeStr = df.format(edate);


            //实际结束时间 >计划结束时
            int result = actualfinishtimeStr.compareTo(actualfinishtimeStr);
            if (result > 0) {//此时备注栏不能为空
                if (row.getCell(15) == null) {
                    return "请说明延迟理由";
                }
                if (row.getCell(15) != null) {
                    row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
                    String remark = row.getCell(15).getStringCellValue();
                    schedule.setRemark(remark);
                }
            }

            schedule.setActualfinishtime(actualfinishtimeStr);
        }

        if (row.getCell(12) == null) {
            return "实际人日不能为空";
        }
        if (row.getCell(12) != null) {
            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
            String actualHumanDateStr = row.getCell(12).getStringCellValue();
            schedule.setActualday(Double.parseDouble(actualHumanDateStr));
        }

        if (row.getCell(13) == null) {
            return "当前状态不能为空";
        }
        if (row.getCell(13) != null) {
            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
            String currentstatusStr = row.getCell(13).getStringCellValue();
            if (!("已完成".equals(currentstatusStr) || "未开始".equals(currentstatusStr) || "正在进行".equals(currentstatusStr) || "暂停".equals(currentstatusStr) || "待定".equals(currentstatusStr) || "待测试".equals(currentstatusStr))) {
                return "当前状态填写有误";
            }

            if ("已完成".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    if (person.equals(accepter)) {
                        return "验收人不能和责任人一致";
                    }
                    schedule.setAccepter(accepter);
                }
            }
            if ("未开始".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    schedule.setAccepter(accepter);
                }
            }
            if ("正在进行".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    schedule.setAccepter(accepter);
                }
            }
            if ("暂停".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    schedule.setAccepter(accepter);
                }
            }
            if ("待定".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    schedule.setAccepter(accepter);
                }
            }
            if ("待测试".equals(currentstatusStr)) {
                if (row.getCell(14) == null) {
                    return "验收人不能为空";
                }
                if (row.getCell(14) != null) {
                    row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
                    String accepter = row.getCell(14).getStringCellValue();
                    schedule.setAccepter(accepter);
                }
            }
            schedule.setCurrentstatus(currentstatusStr);
        }
        return "success";
    }

}
