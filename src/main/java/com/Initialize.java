package com;

import com.entity.*;
import com.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class Initialize {

    @Autowired
    public Initialize(AdministratorServiceImpl administratorService, ClassroomServiceImpl classroomService, CourseServiceImpl courseService, ExamServiceImpl examService, FileMissionServiceImpl fileMissionService, MissionServiceImpl missionService, ReplyServiceImpl replyService, ReplyMissionServiceImpl replyMissionService, ResourceServiceImpl resourceService, TeacherServiceImpl teacherService, TeacherWatchClassroomServiceImpl teacherWatchClassroomService, TitleServiceImpl titleService) {

        TitleEntity titleEntity = new TitleEntity();
        titleEntity.setName("professor");
        titleService.addTitle(titleEntity);

        TitleEntity titleEntity1 = new TitleEntity();
        titleEntity.setName("vice professor");
        titleService.addTitle(titleEntity1);

        TitleEntity titleEntity2 = new TitleEntity();
        titleEntity2.setName("normal teacher");
        titleService.addTitle(titleEntity2);

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAccount("admin123");
        teacherEntity.setPassword("admin123");
        teacherEntity.setName("admin");
        teacherEntity.setDescription("this is a administrator");
        teacherEntity.setGender("male");
        teacherEntity.setPhone(123456789);
        teacherEntity.setEmail("123456@gmail.com");
        teacherEntity.setTitleEntity(titleEntity);
        teacherService.addTeacher(teacherEntity);

        TeacherEntity teacherEntity1 = new TeacherEntity();
        teacherEntity1.setAccount("user123");
        teacherEntity1.setPassword("user123");
        teacherEntity1.setName("user");
        teacherEntity1.setDescription("this is a normal teacher user");
        teacherEntity1.setGender("female");
        teacherEntity1.setPhone(1234567890);
        teacherEntity1.setEmail("111111@gmail.com");
        teacherEntity1.setTitleEntity(titleEntity1);
        teacherService.addTeacher(teacherEntity1);

        TeacherEntity teacherEntity2 = new TeacherEntity();
        teacherEntity2.setAccount("user456");
        teacherEntity2.setPassword("user456");
        teacherEntity2.setName("lin");
        teacherEntity2.setDescription("this teacher is Mr.s lin");
        teacherEntity2.setGender("male");
        teacherEntity2.setPhone(123123123);
        teacherEntity2.setEmail("123123@gmail.com");
        teacherEntity2.setTitleEntity(titleEntity2);
        teacherService.addTeacher(teacherEntity2);

        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setTeacherEntity(teacherEntity);
        administratorService.addAdministrator(administratorEntity);

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName("system design");
        courseEntity.setAdministratorEntity(administratorEntity);
        courseService.addCourse(courseEntity);

        ExamEntity examEntity = new ExamEntity();
        examEntity.setName("system design first");
//        examEntity.setStartTime();
//        examEntity.setEndTime();
//        examEntity.setLastTime();
        examEntity.setStudentNumber(50);
        examEntity.setExamInformationStatus("created");
        examEntity.setCourseEntity(courseEntity);
        examService.addExam(examEntity);

        ClassroomEntity classroomEntity = new ClassroomEntity();
        classroomEntity.setClassInformation("classroom 1");
        classroomEntity.setExamEntity(examEntity);
        classroomService.addClassroom(classroomEntity);

        ClassroomEntity classroomEntity1 = new ClassroomEntity();
        classroomEntity1.setClassInformation("classroom 2");
        classroomEntity1.setExamEntity(examEntity);
        classroomService.addClassroom(classroomEntity1);

        MissionEntity missionEntity = new MissionEntity();
        missionEntity.setMissionType("fileMission");
        missionEntity.setMissionStatus("created");
        missionEntity.setAdministratorEntity(administratorEntity);
        missionService.addMission(missionEntity);

        MissionEntity missionEntity1 = new MissionEntity();
        missionEntity1.setMissionType("replyMission");
        missionEntity1.setMissionStatus("created");
        missionEntity1.setAdministratorEntity(administratorEntity);
        missionService.addMission(missionEntity1);

        FileMissionEntity fileMissionEntity = new FileMissionEntity();
        fileMissionEntity.setName("upload personal file");
        fileMissionEntity.setDescription("upload every teacher's file");
//        fileMissionEntity.setDeadline();
        fileMissionEntity.setMissionEntity(missionEntity);
        fileMissionService.addFileMission(fileMissionEntity);

        ReplyMissionEntity replyMissionEntity = new ReplyMissionEntity();
        replyMissionEntity.setName("reply the request");
        replyMissionEntity.setDescription("every teacher should reply the request");
//        replyMissionEntity.setDeadline();
        replyMissionEntity.setMissionEntity(missionEntity1);
        replyMissionService.addReplyMission(replyMissionEntity);

        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setName("this is a resource");
        resourceEntity.setTeacherEntity(teacherEntity2);
        resourceEntity.setFileMissionEntity(fileMissionEntity);
        resourceService.addResource(resourceEntity);

        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setReplyInformation("this is my reply");
        replyEntity.setWhetherInTime(true);
//     默认为当前时间   replyEntity.setReplyTime();
        replyEntity.setTeacherEntity(teacherEntity1);
        replyEntity.setReplyMissionEntity(replyMissionEntity);
        replyService.addReply(replyEntity);

        TeacherWatchClassroomEntity teacherWatchClassroomEntity = new TeacherWatchClassroomEntity();
        teacherWatchClassroomEntity.setClassroomEntity(classroomEntity);
        teacherWatchClassroomEntity.setTeacherEntity(teacherEntity1);
        teacherWatchClassroomEntity.setTeacherEntity(teacherEntity2);
        teacherWatchClassroomService.addTeacherWatchClassroom(teacherWatchClassroomEntity);


    }
}
