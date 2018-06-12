package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "mission", schema = "teacherworkmanagesystemdatabase")
public class MissionEntity {
    private int id;
    private String missionType;
    private String missionStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    @Id
    @Column(name = "mis_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "missionType")
    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    @Basic
    @Column(name = "mission_status")
    public String getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(String missionStatus) {
        this.missionStatus = missionStatus;
    }

    @Basic
    @Column(name = "mis_createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "mis_updateTime")
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
        MissionEntity that = (MissionEntity) o;
        return id == that.id &&
                Objects.equals(missionType, that.missionType) &&
                Objects.equals(missionStatus, that.missionStatus) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, missionType, missionStatus, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "MissionEntity{" +
                "id=" + id +
                ", missionType='" + missionType + '\'' +
                ", missionStatus='" + missionStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
