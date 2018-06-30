package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 这个类用来储存资源实体的数据
 */
@Entity
@Table(name = "resource", schema = "teacherworkmanagesystemdatabase")
public class ResourceEntity {
    private int id;
    private String name;
    private FileMissionEntity fileMissionEntity;
    private TeacherEntity teacherEntity;

    public ResourceEntity() {

    }

    public ResourceEntity(int id, String name, FileMissionEntity fileMissionEntity, TeacherEntity teacherEntity) {
        this.id = id;
        this.name = name;
        this.fileMissionEntity = fileMissionEntity;
        this.teacherEntity = teacherEntity;
    }

    @OneToOne
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
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


    @Override
    public String toString() {
        return "ResourceEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileMissionEntity=" + fileMissionEntity +
                ", teacherEntity=" + teacherEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceEntity that = (ResourceEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(fileMissionEntity, that.fileMissionEntity) &&
                Objects.equals(teacherEntity, that.teacherEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, fileMissionEntity, teacherEntity);
    }
}
