package me.funnyzhao.mangostreet.bean;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 种类信息实体
 */

public class Category {
    private String categoryName;     //种类名称
    private Date createdAt;
    private String objectId;         //物品Id
    private Date updatedAt;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryName='" + categoryName + '\'' +
                ", createdAt=" + createdAt +
                ", objectId='" + objectId + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
