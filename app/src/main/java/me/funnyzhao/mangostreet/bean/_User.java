package me.funnyzhao.mangostreet.bean;

import java.io.Serializable;

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
    private String tentqq;
    private String tel;
    private String starttime;      //入学时间
    private String school;         //学校
    private String major;          //专业
    private String imageurl;       //头像图片url
    private Boolean identification;//学号认证
    private String department;     //院系
    private Boolean emailVerified; //邮箱认证
    private String email;          //邮箱
    private String createdAt;
    private String updatedAt;
    private String sessionToken;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

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

    public String getTentqq() {
        return tentqq;
    }

    public void setTentqq(String tentqq) {
        this.tentqq = tentqq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
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
