package com.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "physicalPath")
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

    @Basic
    @Column(name = "isFailed")
    public byte getIsFailed() {
        return isFailed;
    }

    public void setIsFailed(byte isFailed) {
        this.isFailed = isFailed;
    }

    @Basic
    @Column(name = "hash")
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
}
