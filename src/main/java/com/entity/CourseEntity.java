package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存课程实体的数据
 */
@Entity
@Table(name = "course", schema = "teacherworkmanagesystemdatabase")
public class CourseEntity {
    private int id;
    private String courseName;
    private Timestamp createTime;
    private Timestamp updateTime;
    private AdministratorEntity administratorEntity;
    private Set<ExamEntity> examEntities;

    public CourseEntity() {
    }

    /**
     * 与考试实体一对多的映射
     * @return 考试实体
     */
    @OneToMany(mappedBy = "courseEntity")
    public Set<ExamEntity> getExamEntities() {
        return examEntities;
    }

    public void setExamEntities(Set<ExamEntity> examEntities) {
        this.examEntities = examEntities;
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

    /**
     * 课程id
     * @return 课程编号
     */
    @Id
    @Column(name = "cou_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cou_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "cou_createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "cou_updateTime")
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
        CourseEntity that = (CourseEntity) o;
        return id == that.id &&
                Objects.equals(courseName, that.courseName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, courseName, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", administratorEntity=" + administratorEntity +
                ", examEntities=" + examEntities +
                '}';
    }

    public CourseEntity(int id, String courseName, Timestamp createTime, Timestamp updateTime, AdministratorEntity administratorEntity, Set<ExamEntity> examEntities) {
        this.id = id;
        this.courseName = courseName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.administratorEntity = administratorEntity;
        this.examEntities = examEntities;
    }
}
