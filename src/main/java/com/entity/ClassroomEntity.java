package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

/**
 * 这个类用来储存教室实体的数据
 */
@Entity
@Table(name = "classroom", schema = "teacherworkmanagesystemdatabase")
public class ClassroomEntity {
    private int id;
    private String classInformation;
    private Calendar createTime;
    private Calendar updateTime;
    private ExamEntity examEntity;

    public ClassroomEntity() {
    }

    /**
     * 与考试实体多对一的映射
     * @return 考试实体
     */
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

    /**
     * 教室的地点信息 如：丹青楼925 或者 城栋楼 1002
     * @return 教室地点
     */
    @Basic
    @Column(name = "cla_information")
    @NotNull
    public String getClassInformation() {
        return classInformation;
    }

    public void setClassInformation(String classInformation) {
        this.classInformation = classInformation;
    }

    public ClassroomEntity(int id, String classInformation, Calendar createTime, Calendar updateTime, ExamEntity examEntity) {
        this.id = id;
        this.classInformation = classInformation;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.examEntity = examEntity;
    }

    @Basic
    @Column(name = "cla_createTime",insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "cla_updateTime", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getUpdateTime() {
        return updateTime;
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

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}
