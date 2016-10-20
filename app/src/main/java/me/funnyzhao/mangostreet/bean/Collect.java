package me.funnyzhao.mangostreet.bean;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 收藏信息实体
 */

public class Collect {
    private Date createdAt;
    private String itemId;     //物品Id
    private String objectId;   //objectId
    private Date updatedAt;
    private String userObjectId;    //收藏人ID

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserObjectId() {
        return userObjectId;
    }

    public void setUserObjectId(String userObjectId) {
        this.userObjectId = userObjectId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "createdAt=" + createdAt +
                ", itemId='" + itemId + '\'' +
                ", objectId='" + objectId + '\'' +
                ", updatedAt=" + updatedAt +
                ", userObjectId='" + userObjectId + '\'' +
                '}';
    }
}
