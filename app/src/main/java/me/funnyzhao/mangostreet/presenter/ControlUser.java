package me.funnyzhao.mangostreet.presenter;

import java.util.HashMap;

import me.funnyzhao.mangostreet.bean._User;

/**
 * Created by funnyzhao .
 * 用户临时数据的处理
 */

public abstract class ControlUser {
    protected HashMap<String,String> hashMap=null;

    protected _User user=new _User();
    /**
     * 用户登录成功后的数据 username、objectId
     * @return
     */
    public abstract void setOnlineData(HashMap<String,String> hashMap);

    /**
     * 设置当前在线用户的信息
     * @param onlineUser
     */
    public abstract void setOnlineUser(_User onlineUser);


}
