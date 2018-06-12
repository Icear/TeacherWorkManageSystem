package com.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeacherWatchExamEntityPK implements Serializable {
    private int examId;
    private int teacherId;

    @Column(name = "exa_no")
    @Id
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    @Column(name = "tea_no")
    @Id
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
        TeacherWatchExamEntityPK that = (TeacherWatchExamEntityPK) o;
        return examId == that.examId &&
                teacherId == that.teacherId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(examId, teacherId);
    }

    @Override
    public String toString() {
        return "TeacherWatchExamEntityPK{" +
                "examId=" + examId +
                ", teacherId=" + teacherId +
                '}';
    }
}
