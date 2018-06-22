package com.entity;


import javax.persistence.*;
import java.util.Objects;

/**
 * 这个类用来存储老师监考教室的数据
 */
@Entity
@Table(name = "teacher_watch_classroom", schema = "teacherworkmanagesystemdatabase")
public class TeacherWatchClassroomEntity {
    private int id;
    private TeacherEntity teacherEntity;
    private ClassroomEntity classroomEntity;

    public TeacherWatchClassroomEntity() {
    }

    public TeacherWatchClassroomEntity(int id, TeacherEntity teacherEntity, ClassroomEntity classroomEntity) {
        this.id = id;
        this.teacherEntity = teacherEntity;
        this.classroomEntity = classroomEntity;
    }

    @Id
    @Column(name = "tea_watch_cla_no")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 与老师实体多对一的映射
     *
     * @return 老师实体
     */
    @ManyToOne
    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public void setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
    }

    /**
     * 与教室实体多对一的映射
     *
     * @return 教室实体
     */
    @ManyToOne
    public ClassroomEntity getClassroomEntity() {
        return classroomEntity;
    }

    public void setClassroomEntity(ClassroomEntity classroomEntity) {
        this.classroomEntity = classroomEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherWatchClassroomEntity that = (TeacherWatchClassroomEntity) o;
        return id == that.id &&
                Objects.equals(teacherEntity, that.teacherEntity) &&
                Objects.equals(classroomEntity, that.classroomEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, teacherEntity, classroomEntity);
    }

    @Override
    public String toString() {
        return "TeacherWatchClassroomEntity{" +
                "id=" + id +
                ", teacherEntity=" + teacherEntity +
                ", classroomEntity=" + classroomEntity +
                '}';
    }
}
