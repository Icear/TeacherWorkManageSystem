package com.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存职称实体的数据
 */
@Entity
@Table(name = "title", schema = "teacherworkmanagesystemdatabase")
public class TitleEntity {
    private int id;
    private String name;
    private Set<TeacherEntity> teacherEntities;

    public TitleEntity() {
    }

    /**
     * 和教师实体一对多的映射
     * @return 老师实体
     */
    @OneToMany(mappedBy = "titleEntity", fetch = FetchType.EAGER)
    public Set<TeacherEntity> getTeacherEntities() {
        return teacherEntities;
    }

    public void setTeacherEntities(Set<TeacherEntity> teacherEntities) {
        this.teacherEntities = teacherEntities;
    }

    @Id
    @Column(name = "tit_no")
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int titNo) {
        this.id = titNo;
    }

    @Basic
    @Column(name = "til_name")
    public String getName() {
        return name;
    }

    public void setName(String tilName) {
        this.name = tilName;
    }

    @Override
    public String toString() {
        return "TitleEntity{" +
                "titNo=" + id +
                ", tilName='" + name + '\'' +
                ", teacherEntities=" + teacherEntities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleEntity that = (TitleEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(teacherEntities, that.teacherEntities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, teacherEntities);
    }

    public TitleEntity(int titNo, String tilName, Set<TeacherEntity> teacherEntities) {
        this.id = titNo;
        this.name = tilName;
        this.teacherEntities = teacherEntities;
    }
}
