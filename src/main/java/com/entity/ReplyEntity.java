package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "reply", schema = "teacherworkmanagesystemdatabase")
public class ReplyEntity {
    private int id;
    private String replyInformation;
    private Timestamp replyTime;
    private Byte whetherInTime;

    @Id
    @Column(name = "rep_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

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
                '}';
    }
}
