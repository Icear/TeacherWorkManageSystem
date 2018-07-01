package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    private Date startTime;
    private Date endTime;
    private int lastTime;
    private Integer studentNumber;
    private String examInformationStatus;
    private Date createTime;
    private Date updateTime;
    private AdministratorEntity administratorEntity;
    private CourseEntity courseEntity;
    private Set<ClassroomEntity> classroomEntities;
//    private Set<TeacherWatchExamEntity> teacherWatchExamEntities;

    public ExamEntity() {
    }

//    /**
//     * 与老师监考实体一对多的映射
//     * @return 考试监考实体
//     */
//    @OneToMany(mappedBy = "examEntity")
//    public Set<TeacherWatchExamEntity> getTeacherWatchExamEntities() {
//        return teacherWatchExamEntities;
//    }
//
//    public void setTeacherWatchExamEntities(Set<TeacherWatchExamEntity> teacherWatchExamEntities) {
//        this.teacherWatchExamEntities = teacherWatchExamEntities;
//    }

    /**
     * 与教室实体一对多的映射
     * @return 教室实体
     */
    @OneToMany(mappedBy = "examEntity", fetch = FetchType.EAGER)
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
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExamEntity(int id, String name, Date startTime, Date endTime, int lastTime, Integer studentNumber, String examInformationStatus, Date createTime, Date updateTime, AdministratorEntity administratorEntity, CourseEntity courseEntity, Set<ClassroomEntity> classroomEntities) {
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
    }

    @Basic
    @Column(name = "startTime")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime")
    public Date getEndTime() {
        return endTime;
    }

    @Basic
    @Column(name = "lastTime")
    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
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

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "exa_createTime", insertable = false, updatable = false, columnDefinition = "Date NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "exa_updateTime", columnDefinition = "Date NOT NULL DEFAULT CURRENT_Date ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamEntity that = (ExamEntity) o;
        return id == that.id &&
                lastTime == that.lastTime &&
                Objects.equals(name, that.name) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(studentNumber, that.studentNumber) &&
                Objects.equals(examInformationStatus, that.examInformationStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(administratorEntity, that.administratorEntity) &&
                Objects.equals(courseEntity, that.courseEntity) &&
                Objects.equals(classroomEntities, that.classroomEntities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, startTime, endTime, lastTime, studentNumber, examInformationStatus, createTime, updateTime, administratorEntity, courseEntity, classroomEntities);
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
//                ", teacherWatchExamEntities=" + teacherWatchExamEntities +
                '}';
    }

}
