package me.funnyzhao.mangostreet.bean.success;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 注册成功响应体
 */

public class SuccessBody {
    private Date createdAt;
    private String  objectId;
    private String  sessionToken;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
    public SuccessBody(){}

    public SuccessBody(Date createdAt, String objectId, String sessionToken) {
        this.createdAt = createdAt;
        this.objectId = objectId;
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "SuccessBody{" +
                "createdAt=" + createdAt +
                ", objectId='" + objectId + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
