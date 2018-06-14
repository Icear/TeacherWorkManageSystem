package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 这个类用来储存任务实体的数据
 */
@Entity
@Table(name = "mission", schema = "teacherworkmanagesystemdatabase")

public class MissionEntity {
    private int id;
    private String missionType;
    private String missionStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private AdministratorEntity administratorEntity;
    private ReplyMissionEntity replyMissionEntity;
    private FileMissionEntity fileMissionEntity;

    public MissionEntity() {
    }

    /**
     * 与文件任务实体一对一的关系
     * @return 文件任务实体
     */
    @OneToOne
    @JoinColumn(unique = true)
    public FileMissionEntity getFileMissionEntity() {
        return fileMissionEntity;
    }

    public void setFileMissionEntity(FileMissionEntity fileMissionEntity) {
        this.fileMissionEntity = fileMissionEntity;
    }

    /**
     * 与回复任务实体的一对一的关系
     * @return 回复任务实体
     */
    @OneToOne
    @JoinColumn(unique = true)
    public ReplyMissionEntity getReplyMissionEntity() {
        return replyMissionEntity;
    }

    public void setReplyMissionEntity(ReplyMissionEntity replyMissionEntity) {
        this.replyMissionEntity = replyMissionEntity;
    }

    @ManyToOne
    public AdministratorEntity getAdministratorEntity() {
        return administratorEntity;
    }

    public void setAdministratorEntity(AdministratorEntity administratorEntity) {
        this.administratorEntity = administratorEntity;
    }

    @Id
    @Column(name = "mis_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 任务类型 共两种： 文件任务/回复任务
     * @return 任务类型
     */
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
                ", administratorEntity=" + administratorEntity +
                ", replyMissionEntity=" + replyMissionEntity +
                ", fileMissionEntity=" + fileMissionEntity +
                '}';
    }

    public MissionEntity(int id, String missionType, String missionStatus, Timestamp createTime, Timestamp updateTime, AdministratorEntity administratorEntity, ReplyMissionEntity replyMissionEntity, FileMissionEntity fileMissionEntity) {
        this.id = id;
        this.missionType = missionType;
        this.missionStatus = missionStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.administratorEntity = administratorEntity;
        this.replyMissionEntity = replyMissionEntity;
        this.fileMissionEntity = fileMissionEntity;
    }
}
