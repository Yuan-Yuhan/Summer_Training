package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity //表的实体
@Table(name="yonghu")  //设置table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增策略
    private Integer uid;//用户ID

    @Column(columnDefinition = "varchar(255) default ''")
    private String opendcode;//登录账户

    @Column(columnDefinition = "varchar(255) default ''")
    private String avatar;//头像

    //基本信息
    @Column(columnDefinition = "varchar(255) default ''")
    private String nikename;//昵称

    @Column(columnDefinition = "varchar(255) default ''")
    private String gender;//性别

    @Column(columnDefinition = "varchar(255) default ''")
    private String birthday;//生日  时间

    @Column(columnDefinition = "varchar(255) default ''")
    private String phone;//联系电话


    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;//更新时间

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;//构建时间

    @Column(columnDefinition = "int default 0")
    private Integer deleted;//逻辑删除  0 存在  1 删除

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOpendcode() {
        return opendcode;
    }

    public void setOpendcode(String opendcode) {
        this.opendcode = opendcode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", opendcode='" + opendcode + '\'' +
                ", avatar='" + avatar + '\'' +
                ", nikename='" + nikename + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", updated=" + updated +
                ", created=" + created +
                ", deleted=" + deleted +
                '}';
    }
}
