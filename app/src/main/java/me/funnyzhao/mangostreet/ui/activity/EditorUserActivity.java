package me.funnyzhao.mangostreet.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.drakeet.materialdialog.MaterialDialog;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.presenter.EditorUserPerImpl;
import me.funnyzhao.mangostreet.presenter.IEditorUserPer;
import me.funnyzhao.mangostreet.util.ImageComp;
import me.funnyzhao.mangostreet.util.UriUtils;
import me.funnyzhao.mangostreet.view.IEditorUserView;

/**
 * Created by funnyzhao .
 * 编辑用户信息
 */

public class EditorUserActivity extends BaseActivity implements IEditorUserView,View.OnClickListener{
    //头部
    @BindView(R.id.ed_iv_back)
    ImageView ivBack;
    @BindView(R.id.ed_iv_done)
    ImageView ivDone;

    //头像
    @BindView(R.id.ed_xfv_user)
    CircleImageView xfvUserImage;

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

    //对话框
    MaterialDialog mMaterialDialog ;
    private KProgressHUD kProgressHUD;  //上传进度条
    private KProgressHUD kProgressHUDone;//保存信息进度条
    //imagePath 图片真实路径
    private String imagePath;
    private static final int CHOOSE_PICTURE=1;
    private int sppositon;
    //上传后的url
    private String imageUrl="";
    private IEditorUserPer iEditorUserPer;
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_editor_user);
    }

    @Override
    protected void initEvents() {
        ivBack.setOnClickListener(this);
        ivDone.setOnClickListener(this);
        xfvUserImage.setOnClickListener(this);
        kProgressHUD= KProgressHUD.create(EditorUserActivity.this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在上传...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setWindowColor(R.color.colorPrimary);
        kProgressHUDone= KProgressHUD.create(EditorUserActivity.this);
        kProgressHUDone.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("保存信息中...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setWindowColor(R.color.colorPrimary);
        initAllEditText();
    }

    @Override
    protected void initDataOrPresenter() {
        iEditorUserPer=new EditorUserPerImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ed_iv_back:
                //返回
                break;
            case R.id.ed_iv_done:
                //完成
                if (etUserName.getText().toString().isEmpty()){
                    showToastHint("用户名不能为空");
                }else {
                    iEditorUserPer.saveData();
                }
                break;
            case R.id.ed_xfv_user:
                showPhotoHint();
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
        if (MangoApplication.getUser().getStarttime()==null){
            spStarttime.setSelection(0);
        }else {
            for (int i=0;i<time.length;i++) {
                if (MangoApplication.getUser().getStarttime()
                        .equals(String.valueOf(time[i]))){
                    spStarttime.setSelection(i);
                    break;
                }
            }
        }
        spStarttime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spStarttime.setSelection(position);
                sppositon=position;
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
        //头像

        String url=MangoApplication.getUser().getImageurl();
        if (url==null ||url.equals("'")){
            xfvUserImage.setImageResource(R.mipmap.head_loading_image);
        }else {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .error(R.mipmap.head_error_image)
                    .into(xfvUserImage);
        }
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
        setSpinner();
    }

    @Override
    public void showPhotoHint() {
        mMaterialDialog = new MaterialDialog(this)
                .setTitle("更换头像")
                .setMessage("确认更换头像吗？")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                        startActivityForResult(intent, CHOOSE_PICTURE);
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                });
        mMaterialDialog.show();
    }

    @Override
    public _User getNewData() {
        _User user=new _User();
        if (!etUserName.getText().toString().equals(MangoApplication.getUser().getUsername())){
            user.setUsername(etUserName.getText().toString());
            MangoApplication.getUser().setUsername(user.getUsername());
        }
        user.setSchool(etSchool.getText().toString());
        user.setDepartment(etDepartment.getText().toString());
        user.setMajor(etMagor.getText().toString());
        user.setTel(Integer.valueOf(etTel.getText().toString()));
        user.setTentqq(Integer.valueOf(etTentqq.getText().toString()));
        user.setStarttime(String.valueOf(timeList.get(sppositon)));
        //用户头像url
        if (!imageUrl.equals("")){
            user.setImageurl(imageUrl);
        }else {
            user.setImageurl(MangoApplication.getUser().getImageurl());
        }
        Logger.d(user);
        return user;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl=imageUrl;
    }

    @Override
    public void showProgress() {
        kProgressHUD.show();
    }

    @Override
    public void stopProgress() {
        kProgressHUD.dismiss();
    }

    @Override
    public void showDoneProgress() {
        kProgressHUDone.show();
    }

    @Override
    public void stopDoneProgress() {
        kProgressHUDone.dismiss();
    }

    @Override
    public void showToastHint(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backUserActivity() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        String uploadPath;
        Bitmap bitmap=null;
        super.onActivityResult(requestCode,resultCode,result);
        switch (requestCode) {
            case CHOOSE_PICTURE:
                if (result != null) {
                    Uri originalUri = result.getData();
                    //获取图片路径
                    imagePath=UriUtils.getRealPathFromUri(this,originalUri);
                    //显示压缩后的图片
                    try {
                        bitmap=ImageComp.getBitmapFormUri(this,result.getData());
                        xfvUserImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //获取压缩后Bitmap的路径
                    uploadPath=ImageComp.getCropBitmapPath(bitmap);
                    //上传图片
                    iEditorUserPer.uploadImage(uploadPath);
                }
                break;
        }
    }

}
