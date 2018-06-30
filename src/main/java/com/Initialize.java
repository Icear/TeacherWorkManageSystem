package com;

import com.dao.*;
import com.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class Initialize {
    private AdministratorEntityDao administratorEntityDao;
    private ClassroomEntityDao classroomEntityDao;
    private CourseEntityDao courseEntityDao;
    private ExamEntityDao examEntityDao;
    private FileMissionEntityDao fileMissionEntityDao;
    private MissionEntityDao missionEntityDao;
    private ReplyEntityDao replyEntityDao;
    private ReplyMissionEntityDao replyMissionEntityDao;
    private ResourceEntityDao resourceEntityDao;
    private TeacherEntityDao teacherEntityDao;
    private TeacherWatchClassroomEntityDao teacherWatchClassroomEntityDao;
    private TitleEntityDao titleEntityDao;

    @Autowired
    public Initialize(AdministratorEntityDao administratorEntityDao, ClassroomEntityDao classroomEntityDao, CourseEntityDao courseEntityDao, ExamEntityDao examEntityDao, FileMissionEntityDao fileMissionEntityDao, MissionEntityDao missionEntityDao, ReplyEntityDao replyEntityDao, ReplyMissionEntityDao replyMissionEntityDao, ResourceEntityDao resourceEntityDao, TeacherEntityDao teacherEntityDao, TeacherWatchClassroomEntityDao teacherWatchClassroomEntityDao, TitleEntityDao titleEntityDao) {
        this.administratorEntityDao = administratorEntityDao;
        this.classroomEntityDao = classroomEntityDao;
        this.courseEntityDao = courseEntityDao;
        this.examEntityDao = examEntityDao;
        this.fileMissionEntityDao = fileMissionEntityDao;
        this.missionEntityDao = missionEntityDao;
        this.replyEntityDao = replyEntityDao;
        this.replyMissionEntityDao = replyMissionEntityDao;
        this.resourceEntityDao = resourceEntityDao;
        this.teacherEntityDao = teacherEntityDao;
        this.teacherWatchClassroomEntityDao = teacherWatchClassroomEntityDao;
        this.titleEntityDao = titleEntityDao;

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setAccount("admin123");
        teacherEntity.setPassword("admin123");
        teacherEntity.setName("admin");
        teacherEntity.setDescription("this is a administrator");
        teacherEntity.setGender("male");
        teacherEntity.setPhone(123456789);
        teacherEntity.setEmail("123456@gmail.com");
        teacherEntityDao.persist(teacherEntity);

        TeacherEntity teacherEntity1 = new TeacherEntity();
        teacherEntity1.setAccount("user123");
        teacherEntity1.setPassword("user123");
        teacherEntity1.setName("user");
        teacherEntity1.setDescription("this is a normal teacher user");
        teacherEntity1.setGender("female");
        teacherEntity1.setPhone(1234567890);
        teacherEntity1.setEmail("111111@gmail.com");
        teacherEntityDao.persist(teacherEntity1);

        TeacherEntity teacherEntity2 = new TeacherEntity();
        teacherEntity2.setAccount("user456");
        teacherEntity2.setPassword("user456");
        teacherEntity2.setName("lin");
        teacherEntity2.setDescription("this teacher is Mr.s lin");
        teacherEntity2.setGender("male");
        teacherEntity2.setPhone(123123123);
        teacherEntity2.setEmail("123123@gmail.com");
        teacherEntityDao.persist(teacherEntity2);

        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setTeacherEntity(teacherEntity);
        administratorEntityDao.persist(administratorEntity);

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName("system design");
        courseEntity.setAdministratorEntity(administratorEntity);
        courseEntityDao.persist(courseEntity);

        ExamEntity examEntity = new ExamEntity();
        examEntity.setName("system design first");
//        examEntity.setStartTime();
//        examEntity.setEndTime();
//        examEntity.setLastTime();
        examEntity.setStudentNumber(50);
        examEntity.setExamInformationStatus("created");
        examEntity.setCourseEntity(courseEntity);
        examEntityDao.persist(examEntity);

        ClassroomEntity classroomEntity = new ClassroomEntity();
        classroomEntity.setClassInformation("classroom 1");
        classroomEntity.setExamEntity(examEntity);
        classroomEntityDao.persist(classroomEntity);

        ClassroomEntity classroomEntity1 = new ClassroomEntity();
        classroomEntity1.setClassInformation("classroom 2");
        classroomEntity1.setExamEntity(examEntity);
        classroomEntityDao.persist(classroomEntity1);

        MissionEntity missionEntity = new MissionEntity();
        missionEntity.setMissionType("fileMission");
        missionEntity.setMissionStatus("created");
        missionEntity.setAdministratorEntity(administratorEntity);
        missionEntityDao.persist(missionEntity);

        MissionEntity missionEntity1 = new MissionEntity();
        missionEntity1.setMissionType("replyMission");
        missionEntity1.setMissionStatus("created");
        missionEntity1.setAdministratorEntity(administratorEntity);
        missionEntityDao.persist(missionEntity1);

        FileMissionEntity fileMissionEntity = new FileMissionEntity();
        fileMissionEntity.setName("upload personal file");
        fileMissionEntity.setDescription("upload every teacher's file");
//        fileMissionEntity.setDeadline();
        fileMissionEntity.setMissionEntity(missionEntity);
        fileMissionEntityDao.persist(fileMissionEntity);

        ReplyMissionEntity replyMissionEntity = new ReplyMissionEntity();
        replyMissionEntity.setName("reply the request");
        replyMissionEntity.setDescription("every teacher should reply the request");
//        replyMissionEntity.setDeadline();
        replyMissionEntity.setMissionEntity(missionEntity1);
        replyMissionEntityDao.persist(replyMissionEntity);

        ResourceEntity resourceEntity = new ResourceEntity();
        resourceEntity.setName("this is a resource");
        resourceEntity.setTeacherEntity(teacherEntity2);
        resourceEntity.setFileMissionEntity(fileMissionEntity);
        resourceEntityDao.persist(resourceEntity);

        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setReplyInformation("this is my reply");
        replyEntity.setWhetherInTime(true);
//     默认为当前时间   replyEntity.setReplyTime();
        replyEntity.setTeacherEntity(teacherEntity1);
        replyEntity.setReplyMissionEntity(replyMissionEntity);
        replyEntityDao.persist(replyEntity);


    }
}
