package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "administrator", schema = "teacherworkmanagesystemdatabase")
public class AdministratorEntity {
    private int id;
    private Timestamp createTime;
    private Timestamp updateTime;
    private TeacherEntity teacherEntity;
    private Set<CourseEntity> courseEntities;
    private Set<ExamEntity> examEntities;
    private Set<MissionEntity> missionEntities;

    @OneToMany(mappedBy = "administratorEntity")
    public Set<MissionEntity> getMissionEntities() {
        return missionEntities;
    }

    public void setMissionEntities(Set<MissionEntity> missionEntities) {
        this.missionEntities = missionEntities;
    }

    @OneToMany(mappedBy = "administratorEntity")
    public Set<ExamEntity> getExamEntities() {
        return examEntities;
    }

    public void setExamEntities(Set<ExamEntity> examEntities) {
        this.examEntities = examEntities;
    }

    @OneToMany(mappedBy = "administratorEntity")
    public Set<CourseEntity> getCourseEntities() {
        return courseEntities;
    }

    public void setCourseEntities(Set<CourseEntity> courseEntities) {
        this.courseEntities = courseEntities;
    }

    @OneToOne(mappedBy = "administratorEntity")
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    @Id
    @Column(name = "adm_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "adm_createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "adm_updateTime")
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
        AdministratorEntity that = (AdministratorEntity) o;
        return id == that.id &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createTime, updateTime);
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
