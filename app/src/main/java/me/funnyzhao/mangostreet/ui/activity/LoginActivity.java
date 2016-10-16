package me.funnyzhao.mangostreet.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.presenter.ILoginPer;
import me.funnyzhao.mangostreet.presenter.LoginImpl;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.ILoginView;

/**
 * Created by funnyzhao .
 * 登录
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener,ILoginView{
    @BindView(R.id.iv_back)
    ImageView ivBack;            //返回键

    @BindView(R.id.et_username)
    EditText etUserName;         //用户名(默认第一次为邮箱)

    @BindView(R.id.et_password)
    EditText etPassWord;         //密码

    @BindView(R.id.bt_login)
    Button btLogin;              //登录按钮

    @BindView(R.id.tv_registered)
    TextView tvRegistered;       //注册账号

    @BindView(R.id.tv_forget)
    TextView tvForget;           //忘记密码（找回密码）

    private ILoginPer iLoginPer;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initEvents() {
        btLogin.setOnClickListener(this);
        tvRegistered.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initDataOrPresenter() {
        iLoginPer=new LoginImpl(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                if( NetWorkUtil.isNetConnect(getApplicationContext())){
                    iLoginPer.userLogin(getUserName(),getPassword());
                }else {
                    iLoginPer.showNoNetWork();
                }
                break;
            case R.id.tv_registered:

                break;
            case R.id.tv_forget:

                break;
            case R.id.iv_back:

                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassWord.getText().toString();
    }

    @Override
    public void clearEditText() {
        etUserName.setText("");
        etPassWord.setText("");
    }

    @Override
    public void showResponseMsg(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetWorkInfo() {
        Toast.makeText(getApplicationContext(),"当前无网络连接",Toast.LENGTH_SHORT).show();
    }
}
