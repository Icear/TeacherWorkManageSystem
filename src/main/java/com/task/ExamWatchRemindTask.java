package com.task;

import com.service.EMSService;
import com.service.TeacherWatchExamService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 监考通知计划任务
 */
@Component
public class ExamWatchRemindTask {

    private EMSService emsService;
    private TeacherWatchExamService teacherWatchExamService;

    @Scheduled(cron = "0 0 8 * * *")
    public void remindExamWatch() {
        //TODO 支持properties


    }

}
