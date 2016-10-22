package me.funnyzhao.mangostreet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.ui.activity.LoginActivity;
import me.funnyzhao.mangostreet.ui.activity.UserInfoActivity;
import me.funnyzhao.mangostreet.ui.adapter.FragmentStateAdapter;
import me.funnyzhao.mangostreet.ui.fragment.NewestFragment;
import me.funnyzhao.mangostreet.ui.fragment.RecommendFragment;
import me.funnyzhao.mangostreet.view.IHomeView;

/**
 * Created by funnyzhao .
 * 主页面
 */
public class HomeActivity extends AppCompatActivity implements IHomeView,View.OnClickListener {
    /**--------------
     * 菜单项
     *--------------*/
    //Toolbar
    @BindView(R.id.homebar)
    Toolbar homebar;
    NavigationView navigationView;

    //DrawerLayout
    @BindView(R.id.drawer_layout_home)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mToggle;


    /**--------------
     * 侧滑菜单头部项
     *---------------*/
    CircleImageView ivUserImage;

    TextView tvUserName;

    TextView tvIdentification;  //学号认证

    TextView tvToLogin;         //提示登录

    /**----------------
     *用户临时数据
     *--------------------*/
    private String username;
    private String objectId;

    //用户头像url、学号认证
    private HashMap<String,Object> hashMap;
    private _User mUser;

    /**-----------------------
     * 滑动组件
     *-------------------------*/
    private String[] tabTitleArray = {"最新发布", "推荐商品"};
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private List<Fragment> fragmentList;
    private NewestFragment newestFragment;
    private RecommendFragment recommendFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initEvents();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //更新数据
        if (isOnline()){
            showUserWidget();
        }else{
            showHintWidget();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawerLayout.closeDrawers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }
    @Override
    public boolean isOnline() {
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        objectId=intent.getStringExtra("objectId");
        mUser=(_User)intent.getSerializableExtra("onlineuser");
        if(username!=null && objectId!=null){
            hashMap=new HashMap<>();
            hashMap.put("imageurl",mUser.getImageurl());
            hashMap.put("identification",mUser.getIdentification());
            return true;
        }
        return false;
    }
    @Override
    public void showUserWidget() {
        tvUserName.setVisibility(View.VISIBLE);
        tvIdentification.setVisibility(View.VISIBLE);
        tvToLogin.setVisibility(View.GONE);
        ivUserImage.setOnClickListener(this);
        checkUserShow();
    }

    @Override
    public void showHintWidget() {
        tvUserName.setVisibility(View.GONE);
        tvIdentification.setVisibility(View.GONE);
        tvToLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void showResponseMsg(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_user:
            case R.id.tv_onlineusername:
                //加载成功进入用户信息页
                Intent intent =new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_identification:
                //用户学号认证页面

                break;
            case R.id.tv_tologin:
                //登录页面
                Intent intent2 =new Intent(this, LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showNetWorkInfo() {
        Toast.makeText(getApplicationContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mUser=new _User();
        //NavigationView版本不稳定的问题，不能使用Bind来找到其中的控件
        navigationView=(NavigationView)findViewById(R.id.navigationView);
        View header=navigationView.getHeaderView(0);
        ivUserImage=(CircleImageView)header.findViewById(R.id.iv_user);
        tvToLogin=(TextView)header.findViewById(R.id.tv_tologin);
        tvUserName=(TextView)header.findViewById(R.id.tv_onlineusername);
        tvIdentification=(TextView)header.findViewById(R.id.tv_identification);
        //-----------------------------------
        initToolbar();
        setTabLayout();
        initViewPager();
        initDrawerLayout();
        //-----------------------------------
        tvToLogin.setOnClickListener(this);
        tvUserName.setOnClickListener(this);
        tvIdentification.setOnClickListener(this);
        if (isOnline()){
            showUserWidget();
        }else{
            showHintWidget();
        }
    }
    private void setTabLayout(){
        tabLayout.setTabMode (TabLayout.MODE_FIXED);
    }
    private void  initViewPager(){
        fragmentList=new ArrayList<>();
        newestFragment=new NewestFragment();
        recommendFragment=new RecommendFragment();
        fragmentList.add(newestFragment);
        fragmentList.add(recommendFragment);
        viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(),fragmentList,tabTitleArray));
        tabLayout.setupWithViewPager(viewPager);
    }
    private void initToolbar(){
        setSupportActionBar(homebar);
        getSupportActionBar().setTitle("芒果二手街");
        homebar.setTitleTextColor(Color.WHITE);
    }

    private void initDrawerLayout(){
         /*以下俩方法设置返回键可用*/
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,homebar,R.string.open,R.string.close);
        //初始化状态
        mToggle.syncState();
        //添加监听
        mDrawerLayout.addDrawerListener(mToggle);
        // navigationView menu点击监听
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
        //同步状态
        mToggle.syncState();
    }

    //选择某一项
    public void  selectDrawerItem(MenuItem menuItem){
        switch (menuItem.getItemId()) {

            // ...
            default:
                Toast.makeText(HomeActivity.this,"menu click",Toast.LENGTH_SHORT).show();
                break;
        }
//        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();

    }



    /**
     * 根据判断显示相应信息
     */
    private void checkUserShow(){
        if(hashMap!=null){
            String imageUrl=(String) hashMap.get("imageurl");
            Boolean iden=(Boolean) hashMap.get("identification");
            //判断当前用户的imageurl、认证
            if(imageUrl==null ||imageUrl.equals("")){
                //使用默认图片
                ivUserImage.setImageResource(R.mipmap.user_icon);
            }else {
                //使用当前图片url加载图片
                Glide.with(this)
                        .load(MangoApplication.getUser().getImageurl())
                        .centerCrop()
                        .error(R.mipmap.user_icon_error)
                        .into(ivUserImage);
            }
            if (iden==null){
                tvIdentification.setText("学号认证(未认证)");
            }else {
                if(iden.booleanValue()){
                    tvIdentification.setText("学号认证(已认证)");
                }else{
                    tvIdentification.setText("学号认证(未认证)");
                }
            }
            tvUserName.setText(MangoApplication.getUser().getUsername());
        }
    }

}
