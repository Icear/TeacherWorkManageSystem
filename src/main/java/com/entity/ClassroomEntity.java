package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "classroom", schema = "teacherworkmanagesystemdatabase")
public class ClassroomEntity {
    private int id;
    private String classInformation;
    private Timestamp createTime;
    private Timestamp updateTime;
    private ExamEntity examEntity;

    @ManyToOne
    public ExamEntity getExamEntity() {
        return examEntity;
    }

    public void setExamEntity(ExamEntity examEntity) {
        this.examEntity = examEntity;
    }

    @Id
    @Column(name = "cla_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cla_information")
    public String getClassInformation() {
        return classInformation;
    }

    public void setClassInformation(String classInformation) {
        this.classInformation = classInformation;
    }

    @Basic
    @Column(name = "cla_createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "cla_updateTime")
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
        ClassroomEntity that = (ClassroomEntity) o;
        return id == that.id &&
                Objects.equals(classInformation, that.classInformation) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, classInformation, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "ClassroomEntity{" +
                "id=" + id +
                ", classInformation='" + classInformation + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", examEntity=" + examEntity +
                '}';
    }
}
