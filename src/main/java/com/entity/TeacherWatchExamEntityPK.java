//package com.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.Objects;
//
///**
// * 这个类用来储存老师监考考试PK实体的数据 因为是多对多的映射关系 所以产生出了两个表
// */
//public class TeacherWatchExamEntityPK implements Serializable {
//    private int id;
//    private int examId;
//    private int teacherId;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column(name = "exa_no")
//    @Id
//    public int getExamId() {
//        return examId;
//    }
//
//    public void setExamId(int examId) {
//        this.examId = examId;
//    }
//
//    @Column(name = "tea_no")
//    @Id
//    public int getTeacherId() {
//        return teacherId;
//    }
//
//    public void setTeacherId(int teacherId) {
//        this.teacherId = teacherId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TeacherWatchExamEntityPK that = (TeacherWatchExamEntityPK) o;
//        return examId == that.examId &&
//                teacherId == that.teacherId;
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(examId, teacherId);
//    }
//
//
//    public TeacherWatchExamEntityPK(int id, int examId, int teacherId) {
//        this.id = id;
//        this.examId = examId;
//        this.teacherId = teacherId;
//    }
//
//    @Override
//    public String toString() {
//        return "TeacherWatchExamEntityPK{" +
//                "id=" + id +
//                ", examId=" + examId +
//                ", teacherId=" + teacherId +
//                '}';
//    }
//
//    public TeacherWatchExamEntityPK() {
//    }
//}
