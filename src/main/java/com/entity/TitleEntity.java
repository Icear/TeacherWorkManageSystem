package com.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存职称实体的数据
 */
@Entity
@Table(name = "title", schema = "teacherworkmanagesystemdatabase")
public class TitleEntity {
    private int titNo;
    private String tilName;
    private Set<TeacherEntity> teacherEntities;

    public TitleEntity() {
    }

    /**
     * 和教师实体一对多的映射
     * @return 老师实体
     */
    @OneToMany(mappedBy = "titleEntity")
    public Set<TeacherEntity> getTeacherEntities() {
        return teacherEntities;
    }

    public void setTeacherEntities(Set<TeacherEntity> teacherEntities) {
        this.teacherEntities = teacherEntities;
    }

    @Id
    @Column(name = "tit_no")
    public int getTitNo() {
        return titNo;
    }

    public void setTitNo(int titNo) {
        this.titNo = titNo;
    }

    @Basic
    @Column(name = "til_name")
    public String getTilName() {
        return tilName;
    }

    public void setTilName(String tilName) {
        this.tilName = tilName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitleEntity that = (TitleEntity) o;
        return titNo == that.titNo &&
                Objects.equals(tilName, that.tilName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(titNo, tilName);
    }

    @Override
    public String toString() {
        return "TitleEntity{" +
                "titNo=" + titNo +
                ", tilName='" + tilName + '\'' +
                ", teacherEntities=" + teacherEntities +
                '}';
    }

    public TitleEntity(int titNo, String tilName, Set<TeacherEntity> teacherEntities) {
        this.titNo = titNo;
        this.tilName = tilName;
        this.teacherEntities = teacherEntities;
    }
}
