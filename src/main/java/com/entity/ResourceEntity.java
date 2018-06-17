package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 这个类用来储存资源实体的数据
 */
@Entity
@Table(name = "resource", schema = "teacherworkmanagesystemdatabase")
public class ResourceEntity {
    private int id;
    private String name;
    private String format;
    private String physicalPath;
    private Timestamp uploadTime;
    private byte isFailed;
    private String hash;
    private FileMissionEntity fileMissionEntity;

    public ResourceEntity() {
    }

    /**
     * 与文件任务多对一的映射
     * @return 文件任务
     */
    @ManyToOne
    public FileMissionEntity getFileMissionEntity() {
        return fileMissionEntity;
    }

    public void setFileMissionEntity(FileMissionEntity fileMissionEntity) {
        this.fileMissionEntity = fileMissionEntity;
    }

    @Id
    @Column(name = "res_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "res_name")
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 资源的格式 如 jpg txt mp3 等
     * @return 资源格式
     */
    @Basic
    @Column(name = "format")
    @NotNull
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 资源的物理路径
     * @return 物理路径
     */
    @Basic
    @Column(name = "physicalPath")
    @NotNull
    public String getPhysicalPath() {
        return physicalPath;
    }

    public void setPhysicalPath(String physicalPath) {
        this.physicalPath = physicalPath;
    }

    @Basic
    @Column(name = "uploadTime")
    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    /**
     * 资源是否失效
     * @return 是/否
     */
    @Basic
    @Column(name = "isFailed")
    public byte getIsFailed() {
        return isFailed;
    }

    public void setIsFailed(byte isFailed) {
        this.isFailed = isFailed;
    }

    /**
     * 资源的哈希值
     * @return 哈希值
     */
    @Basic
    @Column(name = "hash")
    @NotNull
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceEntity that = (ResourceEntity) o;
        return id == that.id &&
                isFailed == that.isFailed &&
                Objects.equals(name, that.name) &&
                Objects.equals(format, that.format) &&
                Objects.equals(physicalPath, that.physicalPath) &&
                Objects.equals(uploadTime, that.uploadTime) &&
                Objects.equals(hash, that.hash);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, format, physicalPath, uploadTime, isFailed, hash);
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", physicalPath='" + physicalPath + '\'' +
                ", uploadTime=" + uploadTime +
                ", isFailed=" + isFailed +
                ", hash='" + hash + '\'' +
                ", fileMissionEntity=" + fileMissionEntity +
                '}';
    }

    public ResourceEntity(int id, String name, String format, String physicalPath, Timestamp uploadTime, byte isFailed, String hash, FileMissionEntity fileMissionEntity) {
        this.id = id;
        this.name = name;
        this.format = format;
        this.physicalPath = physicalPath;
        this.uploadTime = uploadTime;
        this.isFailed = isFailed;
        this.hash = hash;
        this.fileMissionEntity = fileMissionEntity;
    }
}
