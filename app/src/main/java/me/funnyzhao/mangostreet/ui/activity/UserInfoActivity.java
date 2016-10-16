package me.funnyzhao.mangostreet.ui.activity;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmga.metroloading.MetroLoadingView;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.IUserInfoPer;
import me.funnyzhao.mangostreet.presenter.UserInfoPerImpl;
import me.funnyzhao.mangostreet.ui.customview.XfeImageView;
import me.funnyzhao.mangostreet.view.IUserInfoView;

/**
 * Created by funnyzhao .
 * 用户信息展示页
 */

public class UserInfoActivity extends BaseActivity implements IUserInfoView{
    /**
     * 头部按钮
     */
    @BindView(R.id.lv_back)
    ImageView ivBack;

    @BindView(R.id.iv_editor)
    ImageView ivEditor;

    /**
     * 第一个CardView
     */
    @BindView(R.id.cd_view1)
    CardView cardView1;

    @BindView(R.id.xfv_user)
    XfeImageView xfvUserImage;

    @BindView(R.id.tv_usernametext)
    TextView tvUserName;

    @BindView(R.id.tv_deparementtext)
    TextView tvDepart;        //院系

    @BindView(R.id.tv_majortext)
    TextView tvMajor;

    @BindView(R.id.tv_starttimetext)
    TextView tvStartTime;

    /**
     * 第二个CardView
     */
    @BindView(R.id.cd_view2)
    CardView cardView2;

    @BindView(R.id.tv_identificationtext)
    TextView tvIdention;          //学号认证
    /**
     * 第三个CardView
     */
    @BindView(R.id.cd_view3)
    CardView cardView3;

    @BindView(R.id.tv_released)
    TextView tvReleased;        //发布
    /**
     * 第四个CardView
     */
    @BindView(R.id.cd_view4)
    CardView cardView4;

    @BindView(R.id.tv_collection)
    TextView tvCollection;       //收藏

    //进度条
    @BindView(R.id.mv_loading)
    MetroLoadingView metroLoadingView;

    int releasedCount,collectCount;

    private IUserInfoPer iUserInfoPer;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_userinfo);
    }

    @Override
    protected void initEvents() {
        //加载动画
        metroLoadingView.start();
        iUserInfoPer.checkLocalData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initDataOrPresenter() {
        iUserInfoPer=new UserInfoPerImpl(this);
    }


    @Override
    public void setCollectAndReseled() {
        releasedCount= MangoApplication.mapGet("releasedCount");
        collectCount=MangoApplication.mapGet("collectCount");
        if(releasedCount>0){
            tvReleased.setText(String.valueOf(releasedCount));
        }
        if(collectCount>0){
            tvCollection.setText(String.valueOf(collectCount));
        }
    }

    @Override
    public void setUserData(_User userData) {
        tvUserName.setText(userData.getUsername());
        if (userData.getDepartment()==null || userData.getDepartment().equals("")){
            tvDepart.setText("未填写");
        }else{
            tvDepart.setText(userData.getDepartment());
        }
        if (userData.getMajor()==null || userData.getMajor().equals("")){
            tvMajor.setText("未填写");
        }else {
            tvMajor.setText(userData.getMajor());
        }
        if (userData.getStarttime()==null || userData.getStarttime().equals("")){
            tvStartTime.setText("未填写");
        }else {
            tvStartTime.setText(userData.getStarttime());
        }
        if(userData.getIdentification()==null || !userData.getIdentification()){
            tvIdention.setText("未认证");
        }else{
            tvIdention.setText("已认证");
        }
    }

    @Override
    public void showCardViews() {
        cardView1.setVisibility(View.VISIBLE);
        cardView2.setVisibility(View.VISIBLE);
        cardView3.setVisibility(View.VISIBLE);
        cardView4.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        metroLoadingView.stop();
        metroLoadingView.setVisibility(View.GONE);
    }
}
