package com.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;
/**
 * 这个类用来储存管理员实体的数据
 */
@Entity
@Table(name = "administrator", schema = "teacherworkmanagesystemdatabase")
public class AdministratorEntity {

    private int id;
    private Calendar createTime;
    private Calendar updateTime;
    private TeacherEntity teacherEntity;
    private Set<CourseEntity> courseEntities;
    private Set<ExamEntity> examEntities;
    private Set<MissionEntity> missionEntities;

    public AdministratorEntity() {
    }

    /**
     * 与任务实体一对多的映射
     * @return 任务实体
     */
    @OneToMany(mappedBy = "administratorEntity")
    public Set<MissionEntity> getMissionEntities() {
        return missionEntities;
    }

    public void setMissionEntities(Set<MissionEntity> missionEntities) {
        this.missionEntities = missionEntities;
    }

    /**
     * 与考试实体一对多的映射
     * @return 考试实体
     */
    @OneToMany(mappedBy = "administratorEntity")
    public Set<ExamEntity> getExamEntities() {
        return examEntities;
    }

    public void setExamEntities(Set<ExamEntity> examEntities) {
        this.examEntities = examEntities;
    }

    /**
     * 与课程实体一对多的映射
     * @return 课程实体
     */
    @OneToMany(mappedBy = "administratorEntity")
    public Set<CourseEntity> getCourseEntities() {
        return courseEntities;
    }

    public void setCourseEntities(Set<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }

    /**
     * 与老师实体一对一的映射
     * @return 老师实体
     */
    @OneToOne
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    /**
     * 管理员编号
     * @return id
     */
    @Id
    @Column(name = "adm_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public AdministratorEntity(int id, Calendar createTime, Calendar updateTime, TeacherEntity teacherEntity, Set<CourseEntity> courseEntities, Set<ExamEntity> examEntities, Set<MissionEntity> missionEntities) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.teacherEntity = teacherEntity;
        this.courseEntities = courseEntities;
        this.examEntities = examEntities;
        this.missionEntities = missionEntities;
    }

    @Basic
    @Column(name = "adm_createTime",insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "adm_updateTime", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdministratorEntity that = (AdministratorEntity) o;
        return id == that.id &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(teacherEntity, that.teacherEntity) &&
                Objects.equals(courseEntities, that.courseEntities) &&
                Objects.equals(examEntities, that.examEntities) &&
                Objects.equals(missionEntities, that.missionEntities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, updateTime, teacherEntity, courseEntities, examEntities, missionEntities);
    }

    @Override
    public String toString() {
        return "AdministratorEntity{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", teacherEntity=" + teacherEntity +
                ", courseEntities=" + courseEntities +
                ", examEntities=" + examEntities +
                ", missionEntities=" + missionEntities +
                '}';
    }


}
