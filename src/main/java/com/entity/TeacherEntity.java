package com.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Objects;
import java.util.Set;

/**
 * 这个类用来储存老师实体的数据
 */
@Entity
@Table(name = "teacher", schema = "teacherworkmanagesystemdatabase")
public class TeacherEntity {
    private int id;
    private String account;
    private String password;
    private String name;
    private String description;
    private String gender;
    private Integer phone;
    private String email;
    private Calendar createTime;
    private Calendar updateTime;
    private AdministratorEntity administratorEntity;
    private Set<ReplyEntity> replyEntities;
    private Set<TeacherWatchClassroomEntity> teacherWatchClassroomEntities;
    private TitleEntity titleEntity;

    public TeacherEntity() {
    }

    /**
     * 和职称实体多对一的映射
     * @return 职称实体
     */
    @ManyToOne
    public TitleEntity getTitleEntity() {
        return titleEntity;
    }

    public void setTitleEntity(TitleEntity titleEntity) {
        this.titleEntity = titleEntity;
    }


    public TeacherEntity(int id, String account, String password, String name, String description, String gender, Integer phone, String email, Calendar createTime, Calendar updateTime, AdministratorEntity administratorEntity, Set<ReplyEntity> replyEntities, Set<TeacherWatchClassroomEntity> teacherWatchClassroomEntities, TitleEntity titleEntity) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.description = description;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.administratorEntity = administratorEntity;
        this.replyEntities = replyEntities;
        this.teacherWatchClassroomEntities = teacherWatchClassroomEntities;
        this.titleEntity = titleEntity;
    }

    /**
     * 与老师监考教室实体一对多的映射
     *
     * @return 老师监考教室实体
     */
    @OneToMany(mappedBy = "teacherEntity", fetch = FetchType.EAGER)
    public Set<TeacherWatchClassroomEntity> getTeacherWatchClassroomEntities() {
        return teacherWatchClassroomEntities;
    }


    /**
     * 与回复实体一对多的映射
     * @return 回复实体
     */
    @OneToMany(mappedBy = "teacherEntity", fetch = FetchType.EAGER)
    public Set<ReplyEntity> getReplyEntities() {
        return replyEntities;
    }

    public void setReplyEntities(Set<ReplyEntity> replyEntities) {
        this.replyEntities = replyEntities;
    }

    /**
     * 与管理员实体一对一的映射
     * @return 管理员实体
     */
    @OneToOne
    @JoinColumn(unique = true)
    public AdministratorEntity getAdministratorEntity() {
        return administratorEntity;
    }

    public void setAdministratorEntity(AdministratorEntity administratorEntity) {
        this.administratorEntity = administratorEntity;
    }

    @Id
    @Column(name = "tea_no")
    @NotNull
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 教师账号必须是6-18位
     * @return 教师的账号
     */
    @Basic
    @Column(name = "account")
    @NotNull
    @Size( min = 6, max = 18)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 教师的密码最少是6位
     * @return 教师的密码
     */
    @Basic
    @Column(name = "password")
    @NotNull
    @Size( max = 6)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "tea_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tea_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTeacherWatchClassroomEntities(Set<TeacherWatchClassroomEntity> teacherWatchClassroomEntities) {
        this.teacherWatchClassroomEntities = teacherWatchClassroomEntities;
    }

    @Basic
    @Column(name = "tea_createTime",insertable = false, updatable = false, columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "tea_updateTime", columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getUpdateTime() {
        return updateTime;
    }

    //    public TeacherEntity(int id, String account, String password, String name, String description, String gender, Integer phone, String email, Calendar createTime, Calendar updateTime, AdministratorEntity administratorEntity, Set<ReplyEntity> replyEntities/*, Set<TeacherWatchExamEntity> teacherWatchExamEntities*/, TitleEntity titleEntity) {
//        this.id = id;
//        this.account = account;
//        this.password = password;
//        this.name = name;
//        this.description = description;
//        this.gender = gender;
//        this.phone = phone;
//        this.email = email;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//        this.administratorEntity = administratorEntity;
//        this.replyEntities = replyEntities;
//        this.teacherWatchExamEntities = teacherWatchExamEntities;
//        this.titleEntity = titleEntity;
//    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherEntity that = (TeacherEntity) o;
        return id == that.id &&
                Objects.equals(account, that.account) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(administratorEntity, that.administratorEntity) &&
                Objects.equals(replyEntities, that.replyEntities) &&
                Objects.equals(teacherWatchClassroomEntities, that.teacherWatchClassroomEntities) &&
                Objects.equals(titleEntity, that.titleEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, account, password, name, description, gender, phone, email, createTime, updateTime, administratorEntity, replyEntities, teacherWatchClassroomEntities, titleEntity);
    }

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "id=" + id +
                ", account='" + account + '\'' +
//                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", administratorEntity=" + administratorEntity +
                ", replyEntities=" + replyEntities +
                ", teacherWatchClassroomEntities=" + teacherWatchClassroomEntities +
                ", titleEntity=" + titleEntity +
                '}';
    }
}
