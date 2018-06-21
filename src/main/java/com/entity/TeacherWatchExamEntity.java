//package com.entity;
//
//import javax.persistence.*;
//import java.util.Objects;
//
///**
// * 这个类用来储存老师监考考试实体的数据
// */
//@Entity
//@Table(name = "teacher_watch_exam", schema = "teacherworkmanagesystemdatabase")
//@IdClass(TeacherWatchExamEntityPK.class)
//public class TeacherWatchExamEntity {
//    private int id;
//    private TeacherEntity teacherEntity;
//    private ExamEntity examEntity;
//
//    public TeacherWatchExamEntity() {
//    }
//
//    @Id
//    @Column(name = "tea_watch_exam_no")
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @ManyToOne
//    public ExamEntity getExamEntity() {
//        return examEntity;
//    }
//
//    public void setExamEntity(ExamEntity examEntity) {
//        this.examEntity = examEntity;
//    }
//
//    @ManyToOne
//    public TeacherEntity getTeacherEntity() {
//        return teacherEntity;
//    }
//
//    public void setTeacherEntity(TeacherEntity teacherEntity) {
//        this.teacherEntity = teacherEntity;
//    }
//
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TeacherWatchExamEntity that = (TeacherWatchExamEntity) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id);
//    }
//
//    @Override
//    public String toString() {
//        return "TeacherWatchExamEntity{" +
//                "id=" + id +
//                ", teacherEntity=" + teacherEntity +
//                ", examEntity=" + examEntity +
//                '}';
//    }
//
//    public TeacherWatchExamEntity(int id,TeacherEntity teacherEntity, ExamEntity examEntity) {
//        this.id = id;
//        this.teacherEntity = teacherEntity;
//        this.examEntity = examEntity;
//    }
//
//}
