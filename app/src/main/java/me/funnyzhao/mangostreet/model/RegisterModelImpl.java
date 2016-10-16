package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.bean.request.RegisterBody;
import me.funnyzhao.mangostreet.presenter.IRegisterPer;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 * 注册Model.
 */

public class RegisterModelImpl implements IRegisterModel{
    @Override
    public void registerUser(IRegisterPer iRegisterPer, String username, String password) {

        RegisterBody registerBody=new RegisterBody();
        registerBody.setUsername(username);
        registerBody.setEmail(username);
        registerBody.setPassword(password);
        RetrofitRequest.toRegisterUser(iRegisterPer,registerBody);
    }
}
