package me.funnyzhao.mangostreet.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.ui.customview.XfeImageView;

/**
 * Created by funnyzhao .
 * 编辑用户信息
 */

public class EditorUserActivity extends BaseActivity implements View.OnClickListener{
    //头部
    @BindView(R.id.ed_iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_iv_done)
    ImageView ivDone;

    //头像
    @BindView(R.id.ed_xfv_user)
    XfeImageView xfvUserImage;

    //信息
    @BindView(R.id.ed_et_username)
    EditText etUserName;
    @BindView(R.id.ed_et_school)
    EditText etSchool;
    @BindView(R.id.ed_et_department)
    EditText etDepartment;
    @BindView(R.id.ed_et_major)
    EditText etMagor;
    @BindView(R.id.ed_sp_starttime)
    Spinner spStarttime;
    @BindView(R.id.ed_et_tel)
    EditText etTel;
    @BindView(R.id.ed_et_tentqq)
    EditText etTentqq;

    //数据源
    private int[] time={2013,2014,2015,2016};
    private List<Integer> timeList;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_editor_user);
    }

    @Override
    protected void initEvents() {
        ivBack.setOnClickListener(this);
        ivDone.setOnClickListener(this);
        xfvUserImage.setOnClickListener(this);
        initAllEditText();
        setSpinner();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ed_iv_back:
                //返回
                break;
            case R.id.ed_iv_done:
                //完成
                break;
            case R.id.ed_xfv_user:
                //编辑图片
                break;
            default:
                break;
        }
    }

    /**
     * 设置Spinner的数据源和监听事件
     */
    private void setSpinner(){
        timeList = new ArrayList<>();
        for (int i = 0; i < time.length; i++) {
            timeList.add(time[i]);
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, timeList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStarttime.setAdapter(arrayAdapter);
        spStarttime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 初始化EditText
     */
    private void initAllEditText(){
        //初始化数据
        etUserName.setText(MangoApplication.getUser().getUsername());
        if (MangoApplication.getUser().getSchool()==null ||MangoApplication.getUser().getSchool().equals("")){
            etSchool.setHint("未填写");
        }else {
            etSchool.setText(MangoApplication.getUser().getSchool());
        }
        if (MangoApplication.getUser().getDepartment()==null || MangoApplication.getUser().getDepartment().equals("")){
            etDepartment.setHint("未填写");
        }else{
            etDepartment.setText(MangoApplication.getUser().getDepartment());
        }
        if (MangoApplication.getUser().getMajor()==null || MangoApplication.getUser().getMajor().equals("")){
            etMagor.setHint("未填写");
        }else {
            etMagor.setText(MangoApplication.getUser().getMajor());
        }
        if (MangoApplication.getUser().getTel()==null){
            etTel.setHint("未填写");
        }else {
            etTel.setText(String.valueOf(MangoApplication.getUser().getTel()));
        }
        if (MangoApplication.getUser().getTentqq()==null){
            etTentqq.setHint("未填写");
        }else {
            etTentqq.setText(String.valueOf(MangoApplication.getUser().getTentqq()));
        }
    }

}
