package me.funnyzhao.mangostreet.presenter;

import me.funnyzhao.mangostreet.model.IRegisterModel;
import me.funnyzhao.mangostreet.model.RegisterModelImpl;
import me.funnyzhao.mangostreet.view.IRegisterView;

/**
 * Created by funnyzhao .
 */

public class RegisterPerImpl implements IRegisterPer {
    private IRegisterModel iRegisterModel;

    private IRegisterView iRegisterView;

    public RegisterPerImpl(IRegisterView iRegisterView){
        this.iRegisterView=iRegisterView;
        iRegisterModel=new RegisterModelImpl();
    }

    @Override
    public void toRegisterUser(String username, String password) {
        iRegisterModel.registerUser(this,username,password);
    }

    @Override
    public void showRequestInfo(String info) {
        if("注册成功".equals(info)){
            iRegisterView.showSuccesInfo();
            //进入登录页

        }else if("注册失败".equals(info)){
            iRegisterView.showFailureInfo();
            //清空输入框信息
            iRegisterView.clearEditText();

        }else{
            iRegisterView.showRequestFailureInfo();
        }

    }

    @Override
    public void showNoNetWork() {
        iRegisterView.showNetWorkInfo();
    }
}
