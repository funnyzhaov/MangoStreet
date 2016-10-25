package me.funnyzhao.mangostreet.bean.success;

import java.util.Date;

/**
 * Created by funnyzhao .
 * 添加数据响应体
 */

public class AddCollectBody {
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
        return "AddCollectBody{" +
                "createdAt=" + createdAt +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
