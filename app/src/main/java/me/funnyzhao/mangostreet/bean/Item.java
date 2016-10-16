package me.funnyzhao.mangostreet.bean;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 物品信息实体
 */

public class Item {
    private String categoryName;      //种类名称
    private Date createdAt;
    private String itemAddress;       //交易地点
    private String itemDescription;   //物品描述
    private Boolean itemEffective;    //发布是否有效
    private String itemImage;         //物品图片URL
    private String itemName;          //物品名称
    private String itemSchool;        //物品所属学校（交易地点所在学校）
    private String objectId;          //itemId
    private Date updatedAt;
    private String userName;          //发布人(用户名)

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

    public String getItemAddress() {
        return itemAddress;
    }

    public void setItemAddress(String itemAddress) {
        this.itemAddress = itemAddress;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Boolean getItemEffective() {
        return itemEffective;
    }

    public void setItemEffective(Boolean itemEffective) {
        this.itemEffective = itemEffective;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSchool() {
        return itemSchool;
    }

    public void setItemSchool(String itemSchool) {
        this.itemSchool = itemSchool;
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
        return "Item{" +
                "categoryName='" + categoryName + '\'' +
                ", createdAt=" + createdAt +
                ", itemAddress='" + itemAddress + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemEffective=" + itemEffective +
                ", itemImage='" + itemImage + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemSchool='" + itemSchool + '\'' +
                ", objectId='" + objectId + '\'' +
                ", updatedAt=" + updatedAt +
                ", userName='" + userName + '\'' +
                '}';
    }
}
