package me.funnyzhao.mangostreet.bean.success;

/**
 * Created by funnyzhao .
 * 删除收藏记录响应体
 */

public class DeleteCollectBody {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "DeleteCollectBody{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
