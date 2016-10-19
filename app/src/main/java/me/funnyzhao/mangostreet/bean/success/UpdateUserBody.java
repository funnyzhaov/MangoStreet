package me.funnyzhao.mangostreet.bean.success;

/**
 * Created by funnyzhao .
 */

public class UpdateUserBody {
    private String updateAt;

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "UpdateUserBody{" +
                "updateAt='" + updateAt + '\'' +
                '}';
    }
}
