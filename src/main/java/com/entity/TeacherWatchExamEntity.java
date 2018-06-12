package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_watch_exam", schema = "teacherworkmanagesystemdatabase")
@IdClass(TeacherWatchExamEntityPK.class)
public class TeacherWatchExamEntity {
    private int examId;
    private int teacherId;

    @Id
    @Column(name = "exa_no")
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Id
    @Column(name = "tea_no")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherWatchExamEntity that = (TeacherWatchExamEntity) o;
        return examId == that.examId &&
                teacherId == that.teacherId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(examId, teacherId);
    }

    @Override
    public String toString() {
        return "TeacherWatchExamEntity{" +
                "examId=" + examId +
                ", teacherId=" + teacherId +
                '}';
    }
}
