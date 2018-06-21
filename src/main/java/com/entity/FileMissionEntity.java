package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存文件任务实体的数据
 */
@Entity
@Table(name = "file_mission", schema = "teacherworkmanagesystemdatabase")
public class FileMissionEntity {
    private int id;
    private String name;
    private String description;
    private Timestamp deadline;
    private Calendar createTime;
    private Calendar updateTime;
    private MissionEntity missionEntity;
    private Set<ResourceEntity> resourceEntities;

    public FileMissionEntity() {
    }

    /**
     * 与资源实体的一对多的映射
     * @return 资源实体
     */
    @OneToMany(mappedBy = "fileMissionEntity")
    public Set<ResourceEntity> getResourceEntities() {
        return resourceEntities;
    }

    public void setResourceEntities(Set<ResourceEntity> resourceEntities) {
        this.resourceEntities = resourceEntities;
    }

    /**
     * 与任务实体一对一的映射
     * @return 任务实体
     */
    @OneToOne
    @JoinColumn(unique = true)
    public MissionEntity getMissionEntity() {
        return missionEntity;
    }

    public void setMissionEntity(MissionEntity missionEntity) {
        this.missionEntity = missionEntity;
    }

    @Id
    @Column(name = "fil_mis_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fil_mis_name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fil_mis_descrption")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "fil_mis_deadline")
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }


    public FileMissionEntity(int id, String name, String description, Timestamp deadline, Calendar createTime, Calendar updateTime, MissionEntity missionEntity, Set<ResourceEntity> resourceEntities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.missionEntity = missionEntity;
        this.resourceEntities = resourceEntities;
    }

    @Basic
    @Column(name = "fil_mis_createTime",insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "fil_mis_updateTime", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getUpdateTime() {
        return updateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileMissionEntity that = (FileMissionEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, deadline, createTime, updateTime);
    }

    @Override
    public String toString() {
        return "FileMissionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", missionEntity=" + missionEntity +
                ", resourceEntities=" + resourceEntities +
                '}';
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}
