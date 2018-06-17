package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存回复任务实体的数据
 */
@Entity
@Table(name = "reply_mission", schema = "teacherworkmanagesystemdatabase")
public class ReplyMissionEntity {
    private int id;
    private String name;
    private String description;
    private Timestamp deadline;
    private Byte status;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Set<ReplyEntity> replyEntities;
    private MissionEntity missionEntity;

    public ReplyMissionEntity() {
    }

    /**
     * 与任务实体一对一的映射
     * @return 任务实体
     */
    @OneToOne(mappedBy = "replyMissionEntity")
    public MissionEntity getMissionEntity() {
        return missionEntity;
    }

    public void setMissionEntity(MissionEntity missionEntity) {
        this.missionEntity = missionEntity;
    }

    /**
     * 与回复实体一对多的映射
     * @return 回复实体
     */
    @OneToMany(mappedBy = "replyMissionEntity")
    public Set<ReplyEntity> getReplyEntities() {
        return replyEntities;
    }

    public void setReplyEntities(Set<ReplyEntity> replyEntities) {
        this.replyEntities = replyEntities;
    }

    @Id
    @Column(name = "rep_mis_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rep_mis_name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "rep_mis_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "rep_mis_deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "rep_mis_status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "rep_mis_createTime",insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "rep_mis_updateTime", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" + "ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
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
        ReplyMissionEntity that = (ReplyMissionEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, deadline, status, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "ReplyMissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", replyEntities=" + replyEntities +
                ", missionEntity=" + missionEntity +
                '}';
    }

    public ReplyMissionEntity(int id, String name, String description, Timestamp deadline, Byte status, Timestamp createTime, Timestamp updateTime, Set<ReplyEntity> replyEntities, MissionEntity missionEntity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.replyEntities = replyEntities;
        this.missionEntity = missionEntity;
    }
}
