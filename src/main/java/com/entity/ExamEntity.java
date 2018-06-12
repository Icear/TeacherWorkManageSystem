package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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

    @Basic
    @Column(name = "studentNumber")
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

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
                '}';
    }
}
