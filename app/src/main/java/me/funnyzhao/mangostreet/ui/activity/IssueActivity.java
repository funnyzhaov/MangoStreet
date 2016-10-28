package me.funnyzhao.mangostreet.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.funnyzhao.mangostreet.BaseActivity;
import me.funnyzhao.mangostreet.R;
import me.funnyzhao.mangostreet.bean.Categorys;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.presenter.IIssuePer;
import me.funnyzhao.mangostreet.presenter.IssuePerImpl;
import me.funnyzhao.mangostreet.util.ImageComp;
import me.funnyzhao.mangostreet.util.NetWorkUtil;
import me.funnyzhao.mangostreet.view.IIssueView;

/**
 * Created by funnyzhao .
 * 发布
 */

public class IssueActivity extends BaseActivity implements View.OnClickListener,IIssueView {
    @BindView(R.id.is_iv_back)
    ImageView isBack;
    @BindView(R.id.is_iv_done)
    ImageView ivDone;
    @BindView(R.id.is_item_image)
    ImageView itemImage;
    @BindView(R.id.is_et_title)
    EditText etTitle;
    @BindView(R.id.is_et_price)
    EditText etPrice;
    @BindView(R.id.is_et_address)
    EditText etAddress;
    @BindView(R.id.is_et_description)
    EditText etDesciption;
    @BindView(R.id.is_sp_category)
    Spinner spCategory;

    //分类适配器-----------------
    private List<String> categoryList;
    private ArrayAdapter arrayAdapter;
    private String[] mCategorys;
    //位置
    private int sppositon;
    //---------------------------
    //压缩后的图片路径
    private String uploadPath;
    private static final int CHOOSE_PICTURE=1;
    //---------------------------

    private IIssuePer iIssuePer;
    //----------------------------
    private KProgressHUD kProgressHUDImage;  //图片上传进度条
    private KProgressHUD kProgressHUDItem;  //物品上传进度条
    @Override
    protected void setNowContentView() {
        setContentView(R.layout.activity_issue);
    }

    @Override
    protected void initEvents() {
        //不获取键盘的焦点
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setLisenter();
        setSpinner();
        setkProgressHUDImage();
        setkProgressHUDItem();
    }

    @Override
    protected void initDataOrPresenter() {
        iIssuePer=new IssuePerImpl(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.is_iv_back:
                finish();
            break;
            case R.id.is_iv_done:
                doDone();
                break;
            case R.id.is_item_image:
                showPhotoHint();
                break;
            default:
                break;
        }
    }

    /**
     * 设置图片上传进度条
     */
    private void setkProgressHUDImage(){
        kProgressHUDImage= KProgressHUD.create(IssueActivity.this);
        kProgressHUDImage.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("上传图片中...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setWindowColor(R.color.colorPrimary);
    }

    /**
     * 设置物品信息上传进度条
     */
    private void setkProgressHUDItem(){
        kProgressHUDItem= KProgressHUD.create(IssueActivity.this);
        kProgressHUDItem.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("物品发布中...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .setWindowColor(R.color.colorPrimary);
    }

    /**
     * 设置监听事件
     */
    private void setLisenter(){
        isBack.setOnClickListener(this);
        ivDone.setOnClickListener(this);
        itemImage.setOnClickListener(this);
    }
    private void setSpinner(){
        categoryList = new ArrayList<>();
        mCategorys=Categorys.getCategorys();
        for (int i = 0; i <mCategorys.length; i++) {
            categoryList.add(mCategorys[i]);
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, categoryList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(arrayAdapter);
        spCategory.setSelection(0);
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spCategory.setSelection(position);
                sppositon=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getChoosePhoto() {
        return uploadPath;
    }


    @Override
    public Item getItemInfo() {
        Item item=new Item();
        item.setItemName(etTitle.getText().toString());
        item.setItemDescription(etDesciption.getText().toString());
        item.setPrice(etPrice.getText().toString());
        item.setItemAddress(etAddress.getText().toString());
        item.setCategoryName(mCategorys[sppositon]);
        return item;
    }


    @Override
    public KProgressHUD getImageHUD() {
        return kProgressHUDImage;
    }

    @Override
    public KProgressHUD getItemHUD() {
        return kProgressHUDItem;
    }

    @Override
    public void showNetWorkInfo() {
        Toast.makeText(this,"无网络",Toast.LENGTH_SHORT).show();
    }

    /**
     * 执行完成按钮
     */
    private void doDone(){
        //检查合法后执行发布
        if (checkInput()){
            if( NetWorkUtil.isNetConnect(getApplicationContext())){
                iIssuePer.issueItem();
            }else {
                showNetWorkInfo();
            }
        }
    }

    /**
     * 检查用户的编辑是否可以发布
     * @return
     */
    private boolean checkInput(){
        boolean isOk=false;
        if (etTitle.getText().toString().equals("")||etTitle.getText().toString()==null){
            showMsg("物品名称呢？");
        }else if (etPrice.getText().toString().equals("")||etPrice.getText().toString()==null){
            showMsg("价格呢？");
        }else if (etAddress.getText().toString().equals("")||etAddress.getText().toString()==null){
            showMsg("地点呢？");
        }else if(etDesciption.getText().toString().equals("")||etDesciption.getText().toString()==null){
            showMsg("描述得有吧？");
        }else if(uploadPath==null|| uploadPath.equals("")){
            showMsg("没有图片谁信呐！");
        } else {
            isOk=true;
        }
        return isOk;
    }

    /**
     * ，打开相册,选择图片
     */
    private void showPhotoHint() {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        //压缩后的图片路径
        Bitmap bitmap=null;
        super.onActivityResult(requestCode,resultCode,result);
        switch (requestCode) {
            case CHOOSE_PICTURE:
                if (result != null) {
                    //显示压缩后的图片
                    try {
                        bitmap= ImageComp.getBitmapFormUri(this,result.getData());
                        itemImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //获取压缩后Bitmap的路径
                    uploadPath=ImageComp.getCropBitmapPath(bitmap);
                    //真实路径不压缩的图片
//                     Uri originalUri = result.getData();
//                    uploadPath= UriUtils.getRealPathFromUri(this,originalUri);
                }
                break;
        }
    }

}
