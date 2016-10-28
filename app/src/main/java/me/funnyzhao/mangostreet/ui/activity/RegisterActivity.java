package me.funnyzhao.mangostreet.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.presenter.IRegisterPer;
import me.funnyzhao.mangostreet.presenter.RegisterPerImpl;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.IRegisterView;

/**
 * Created by funnyzhao .
 * 注册
 */

public class RegisterActivity extends BaseActivity implements IRegisterView{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_username)
     EditText etUserName;         //邮箱

    @BindView(R.id.et_password)
     EditText etPassWord;         //密码

    @BindView(R.id.bt_registered)
     Button btRegister;           //注册按钮

    private IRegisterPer iRegisterPer;

    private KProgressHUD kProgressHUD;  //注册进度条

    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_registered);
    }

    @Override
    protected void initEvents() {
        kProgressHUD= KProgressHUD.create(RegisterActivity.this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("注册验证中...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setWindowColor(R.color.colorPrimary);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( NetWorkUtil.isNetConnect(getApplicationContext())){
                 iRegisterPer.toRegisterUser(getUsername(),getPassword());
               }else{
                   iRegisterPer.showNoNetWork();
               }
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public String getUsername() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassWord.getText().toString();
    }

    @Override
    public void showSuccesInfo() {
        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureInfo() {
        Toast.makeText(getApplicationContext(),"注册失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRequestFailureInfo() {
        Toast.makeText(getApplicationContext(),"请求失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearEditText() {
        etUserName.setText("");
        etPassWord.setText("");
    }

    @Override
    public KProgressHUD getKprogressHUD() {
        return kProgressHUD;
    }

    @Override
    protected void initDataOrPresenter() {
        iRegisterPer=new RegisterPerImpl(this,this);
    }

    @Override
    public void showNetWorkInfo() {
        Toast.makeText(getApplicationContext(),"当前无网络连接",Toast.LENGTH_SHORT).show();
    }
}
