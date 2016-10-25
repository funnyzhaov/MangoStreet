package me.funnyzhao.mangostreet.bean.request;

/**
 * Created by funnyzhao .
 * 收藏信息请求体
 */

public class CollectBody {
    private String userObjectId;
    private String itemId;

    public String getUserObjectId() {
        return userObjectId;
    }

    public void setUserObjectId(String userObjectId) {
        this.userObjectId = userObjectId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public CollectBody(String userObjectId, String itemId) {
        this.userObjectId = userObjectId;
        this.itemId = itemId;
    }
}
