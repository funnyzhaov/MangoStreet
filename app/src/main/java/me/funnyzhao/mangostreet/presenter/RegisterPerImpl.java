package me.funnyzhao.mangostreet.presenter;

import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.model.IRegisterModel;
import me.funnyzhao.mangostreet.model.RegisterModelImpl;
import me.funnyzhao.mangostreet.view.IRegisterView;

/**
 * Created by funnyzhao .
 */

public class RegisterPerImpl implements IRegisterPer {
    private IRegisterModel iRegisterModel;

    private IRegisterView iRegisterView;
    private BaseActivity mContext;
    public RegisterPerImpl(IRegisterView iRegisterView,BaseActivity mContext){
        this.iRegisterView=iRegisterView;
        iRegisterModel=new RegisterModelImpl();
        this.mContext=mContext;
    }

    @Override
    public void toRegisterUser(String username, String password) {
        iRegisterView.getKprogressHUD().show();
        iRegisterModel.registerUser(this,username,password);
    }

    @Override
    public void showRequestInfo(String info) {
        if("注册成功".equals(info)){
            iRegisterView.getKprogressHUD().dismiss();
            iRegisterView.showSuccesInfo();
            //进入登录页
//            Intent intent=new Intent(mContext, LoginActivity.class);
//            mContext.startActivity(intent);
            mContext.finish();
        }else if("注册失败".equals(info)){
            iRegisterView.getKprogressHUD().dismiss();
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
