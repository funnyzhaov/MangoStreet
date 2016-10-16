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
    private String userName;    //收藏人(用户名)

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "createdAt=" + createdAt +
                ", itemId='" + itemId + '\'' +
                ", objectId='" + objectId + '\'' +
                ", updatedAt=" + updatedAt +
                ", userName='" + userName + '\'' +
                '}';
    }
}
