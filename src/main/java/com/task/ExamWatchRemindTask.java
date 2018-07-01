package com.task;

import com.entity.TeacherWatchClassroomEntity;
import com.service.EMSService;
import com.service.TeacherWatchClassroomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class ExamWatchRemindTask {
    private Logger logger = LogManager.getLogger(ExamWatchRemindTask.class);

    private TeacherWatchClassroomService teacherWatchClassroomEntityDao;
    private EMSService emsService;

    @Value("${EMS_CONTENT_TEMPLATE}")
    private String emsContentTemplate;


    @Autowired
    public ExamWatchRemindTask(TeacherWatchClassroomService teacherWatchClassroomEntityDao, EMSService emsService) {
        this.teacherWatchClassroomEntityDao = teacherWatchClassroomEntityDao;
        this.emsService = emsService;
    }

    @Scheduled(cron = "* * 8 * * *")
    public void remind() {

        //扫描所有的ExamWatch
        //找到开考时间在明天的考试
        //搜集信息并发送
        for (TeacherWatchClassroomEntity teacherWatchClassroomEntity : teacherWatchClassroomEntityDao.findTeacherWatchClassrooms()) {
            Date examStartTime = teacherWatchClassroomEntity.getClassroomEntity().getExamEntity().getStartTime();//考试开始时间
            if (compareDate(new Calendar.Builder().setInstant(examStartTime).build(), Calendar.getInstance()) != 1) {
                continue;
            }
            // 符合要求，准备发送短信
            String teacherName = teacherWatchClassroomEntity.getTeacherEntity().getName();//教师
            String examName = teacherWatchClassroomEntity.getClassroomEntity().getExamEntity().getName();//考试名
            Date examEndTime = teacherWatchClassroomEntity.getClassroomEntity().getExamEntity().getEndTime();//考试结束时间
            String classroom = teacherWatchClassroomEntity.getClassroomEntity().getClassInformation();//教室信息

            // 填充短信模板生成内容
            String emsContent = MessageFormat.format(emsContentTemplate, teacherName, examName, examStartTime, examEndTime, classroom);
            logger.debug(emsContent);

            // 发送短信
            Integer phone = teacherWatchClassroomEntity.getTeacherEntity().getPhone();//获得手机号码
            //检查教师是否已设置手机号
            if (phone == null) {
                logger.warn("teacher " + teacherName + " did not set phone number, ems send failed");
                continue;
            }
            emsService.sendEMS(phone.toString(), emsContent);
        }
    }

    /**
     * 比较两个日期大小，只比较到日
     *
     * @param first  第一个
     * @param second 第二个
     * @return 两个日期相差的天数（第一个 - 第二个）
     */
    private int compareDate(Calendar first, Calendar second) {
        return first.get(Calendar.DAY_OF_YEAR) - second.get(Calendar.DAY_OF_YEAR);
    }
}
