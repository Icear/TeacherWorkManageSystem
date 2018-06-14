package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存考试实体的数据
 */
@Entity
@Table(name = "exam", schema = "teacherworkmanagesystemdatabase")
public class ExamEntity {
    private int id;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp lastTime;
    private Integer studentNumber;
    private String examInformationStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private AdministratorEntity administratorEntity;
    private CourseEntity courseEntity;
    private Set<ClassroomEntity> classroomEntities;
    private Set<TeacherWatchExamEntity> teacherWatchExamEntities;

    public ExamEntity() {
    }

    /**
     * 与老师监考实体一对多的映射
     * @return 考试监考实体
     */
    @OneToMany(mappedBy = "examEntity")
    public Set<TeacherWatchExamEntity> getTeacherWatchExamEntities() {
        return teacherWatchExamEntities;
    }

    public void setTeacherWatchExamEntities(Set<TeacherWatchExamEntity> teacherWatchExamEntities) {
        this.teacherWatchExamEntities = teacherWatchExamEntities;
    }

    /**
     * 与教室实体一对多的映射
     * @return 教室实体
     */
    @OneToMany(mappedBy = "examEntity")
    public Set<ClassroomEntity> getClassroomEntities() {
        return classroomEntities;
    }

    public void setClassroomEntities(Set<ClassroomEntity> classroomEntities) {
        this.classroomEntities = classroomEntities;
    }

    /**
     * 与课程实体多对一的映射
     * @return 课程实体
     */
    @ManyToOne
    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    /**
     * 与管理员实体多对一的映射
     * @return 管理员实体
     */
    @ManyToOne
    public AdministratorEntity getAdministratorEntity() {
        return administratorEntity;
    }

    public void setAdministratorEntity(AdministratorEntity administratorEntity) {
        this.administratorEntity = administratorEntity;
    }

    @Id
    @Column(name = "exa_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "exa_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "lastTime")
    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 考试应包含学生的人数
     * @return 学生人数
     */
    @Basic
    @Column(name = "studentNumber")
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * 考试信息分配状态/是否分配给老师 一共用3种状态：①未分配 ②已分配 3.已完成
     * @return 考试信息分配状态
     */
    @Basic
    @Column(name = "examInformationStatus")
    public String getExamInformationStatus() {
        return examInformationStatus;
    }

    public void setExamInformationStatus(String examInformationStatus) {
        this.examInformationStatus = examInformationStatus;
    }

    @Basic
    @Column(name = "exa_createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "exa_updateTime")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamEntity that = (ExamEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(studentNumber, that.studentNumber) &&
                Objects.equals(examInformationStatus, that.examInformationStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, startTime, endTime, lastTime, studentNumber, examInformationStatus, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "ExamEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", lastTime=" + lastTime +
                ", studentNumber=" + studentNumber +
                ", examInformationStatus='" + examInformationStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", administratorEntity=" + administratorEntity +
                ", courseEntity=" + courseEntity +
                ", classroomEntities=" + classroomEntities +
                ", teacherWatchExamEntities=" + teacherWatchExamEntities +
                '}';
    }

    public ExamEntity(int id, String name, Timestamp startTime, Timestamp endTime, Timestamp lastTime, Integer studentNumber, String examInformationStatus, Timestamp createTime, Timestamp updateTime, AdministratorEntity administratorEntity, CourseEntity courseEntity, Set<ClassroomEntity> classroomEntities, Set<TeacherWatchExamEntity> teacherWatchExamEntities) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lastTime = lastTime;
        this.studentNumber = studentNumber;
        this.examInformationStatus = examInformationStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.administratorEntity = administratorEntity;
        this.courseEntity = courseEntity;
        this.classroomEntities = classroomEntities;
        this.teacherWatchExamEntities = teacherWatchExamEntities;
    }

}
