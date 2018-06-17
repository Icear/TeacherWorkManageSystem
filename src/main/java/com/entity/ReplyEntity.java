package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 这个类用来储存回复实体的数据
 */
@Entity
@Table(name = "reply", schema = "teacherworkmanagesystemdatabase")
public class ReplyEntity {
    private int id;
    private String replyInformation;
    private Timestamp replyTime;
    private Byte whetherInTime;
    private TeacherEntity teacherEntity;
    private ReplyMissionEntity replyMissionEntity;

    public ReplyEntity() {
    }

    /**
     * 与回复任务实体多对一的映射
     * @return 回复任务实体
     */
    @ManyToOne
    public ReplyMissionEntity getReplyMissionEntity() {
        return replyMissionEntity;
    }

    public void setReplyMissionEntity(ReplyMissionEntity replyMissionEntity) {
        this.replyMissionEntity = replyMissionEntity;
    }

    /**
     * 与老师实体多对一的映射
     * @return 老师实体
     */
    @ManyToOne
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    @Id
    @Column(name = "rep_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 回复内容
     * @return 回复内容
     */
    @Basic
    @Column(name = "rep_information")
    public String getReplyInformation() {
        return replyInformation;
    }

    public void setReplyInformation(String replyInformation) {
        this.replyInformation = replyInformation;
    }

    @Basic
    @Column(name = "rep_time")
    @NotNull
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 是否在规定时间内回复
     * @return 是/否
     */
    @Basic
    @Column(name = "whetherInTime")
    public Byte getWhetherInTime() {
        return whetherInTime;
    }

    public void setWhetherInTime(Byte whetherInTime) {
        this.whetherInTime = whetherInTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplyEntity that = (ReplyEntity) o;
        return id == that.id &&
                Objects.equals(replyInformation, that.replyInformation) &&
                Objects.equals(replyTime, that.replyTime) &&
                Objects.equals(whetherInTime, that.whetherInTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, replyInformation, replyTime, whetherInTime);
    }

    @Override
    public String toString() {
        return "ReplyEntity{" +
                "id=" + id +
                ", replyInformation='" + replyInformation + '\'' +
                ", replyTime=" + replyTime +
                ", whetherInTime=" + whetherInTime +
                ", teacherEntity=" + teacherEntity +
                ", replyMissionEntity=" + replyMissionEntity +
                '}';
    }

    public ReplyEntity(int id, String replyInformation, Timestamp replyTime, Byte whetherInTime, TeacherEntity teacherEntity, ReplyMissionEntity replyMissionEntity) {
        this.id = id;
        this.replyInformation = replyInformation;
        this.replyTime = replyTime;
        this.whetherInTime = whetherInTime;
        this.teacherEntity = teacherEntity;
        this.replyMissionEntity = replyMissionEntity;
    }
}
