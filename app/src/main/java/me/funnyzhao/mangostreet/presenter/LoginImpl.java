package me.funnyzhao.mangostreet.presenter;

import android.content.Intent;

import java.util.HashMap;

import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.HomeActivity;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.model.ILoginModel;
import me.funnyzhao.mangostreet.model.LoginModelImpl;
import me.funnyzhao.mangostreet.view.ILoginView;

/**
 * Created by funnyzhao .
 */

public class LoginImpl extends ControlUser implements ILoginPer {
    private ILoginView iLoginView;

    private ILoginModel iLoginModel;

    private BaseActivity mContext;

    public LoginImpl(ILoginView iLoginView, BaseActivity context){
        this.iLoginView=iLoginView;
        iLoginModel=new LoginModelImpl();
        this.mContext=context;
    }
    @Override
    public void userLogin(String username, String password) {
        if (username!=null && password!=null){
            if("".equals(username) || "".equals(password)){
                iLoginView.showResponseMsg("输入不合法!");
            }else {
                iLoginModel.userLogin(this, username, password, this);
            }
        }
    }

    @Override
    public void setOnlineData(HashMap<String, String> hashMap) {
        super.hashMap=hashMap;
    }

    @Override
    public void setOnlineUser(_User onlineUser) {
        super.user=onlineUser;
    }

    @Override
    public void showNoNetWork() {
        iLoginView.showNetWorkInfo();
    }

    @Override
    public void showRequestInfo(String info) {
        if("用户名或密码错误!".equals(info) || "请求失败".equals(info)) {
            iLoginView.showResponseMsg(info);
            iLoginView.clearEditText();
        }else {
            iLoginView.showResponseMsg(info);
            //登录成功进入首页
            Intent intent=new Intent(mContext, HomeActivity.class);
            intent.putExtra("username",hashMap.get("username"));
            intent.putExtra("objectId",hashMap.get("objectId"));
            intent.putExtra("onlineuser",user);
            mContext.startActivity(intent);
            mContext.finish();
        }
    }
}
