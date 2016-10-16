package me.funnyzhao.mangostreet.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by funnyzhao .
 * 用户
 */

public class _User implements Serializable{
    private String objectId;
    private String username;
    private String password;
    private Boolean mobilePhoneNumberVerified;
    private String mobilePhoneNumber;
    private Number tentqq;
    private Number tel;
    private String starttime;      //入学时间
    private String school;         //学校
    private String major;          //专业
    private String imageurl;       //头像图片url
    private Boolean identification;//学号认证
    private String department;     //院系
    private Boolean emailVerified; //邮箱认证
    private String email;          //邮箱
    private Date createdAt;
    private Date updatedAt;

    public String getObjectId() {
        return objectId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getMobilePhoneNumberVerified() {
        return mobilePhoneNumberVerified;
    }

    public void setMobilePhoneNumberVerified(Boolean mobilePhoneNumberVerified) {
        this.mobilePhoneNumberVerified = mobilePhoneNumberVerified;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public Number getTentqq() {
        return tentqq;
    }

    public void setTentqq(Number tentqq) {
        this.tentqq = tentqq;
    }

    public Number getTel() {
        return tel;
    }

    public void setTel(Number tel) {
        this.tel = tel;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Boolean getIdentification() {
        return identification;
    }

    public void setIdentification(Boolean identification) {
        this.identification = identification;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public _User() {

    }
    public _User(String objectid) {
        this.objectId = objectid;
    }

    @Override
    public String toString() {
        return "_User{" +
                "objectId='" + objectId + '\'' +
                ", username='" + username + '\'' +
                ", mobilePhoneNumberVerified=" + mobilePhoneNumberVerified +
                ", mobilePhoneNumber='" + mobilePhoneNumber + '\'' +
                ", tentqq=" + tentqq +
                ", tel=" + tel +
                ", starttime='" + starttime + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", identification=" + identification +
                ", department='" + department + '\'' +
                ", emailVerified=" + emailVerified +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
