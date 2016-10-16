package me.funnyzhao.mangostreet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by funnyzhao .
 *
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNowContentView();
        initButterKnife();
        initDataOrPresenter();
        initEvents();
    }


    /**
     *依赖注入的绑定
     */
    private void initButterKnife(){
        ButterKnife.bind(this);
    }

    /**
     * 设置当前view视图
     */
    protected abstract void  setNowContentView();

    /**
     * 初始化事件
     */
    protected abstract  void initEvents() ;

    /**
     *初始化数据,可选择性的重写
     */
    protected void initDataOrPresenter(){}

}
