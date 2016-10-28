package me.funnyzhao.mangostreet.bean.success;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 响应体
 */

public class AddItemBody {
    private Date createdAt;
    private String objectId;

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

    @Override
    public String toString() {
        return "AddItemBody{" +
                "createdAt=" + createdAt +
                ", objectId='" + objectId + '\'' +
                '}';
    }

}
