package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.ControlUser;
import me.funnyzhao.mangostreet.presenter.ILoginPer;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 */

public class LoginModelImpl implements ILoginModel {
    @Override
    public void userLogin(ILoginPer iLoginPer, String username, String password, ControlUser controlUserPer) {
        _User user=new _User();
        user.setUsername(username);
        user.setPassword(password);
        RetrofitRequest.toCheckLogin(iLoginPer,user,controlUserPer);
    }
}
