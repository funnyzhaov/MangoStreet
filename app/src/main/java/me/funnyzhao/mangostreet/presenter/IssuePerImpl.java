package me.funnyzhao.mangostreet.presenter;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.ui.activity.IssueActivity;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;
import me.funnyzhao.mangostreet.util.request.UploadImage;
import me.funnyzhao.mangostreet.view.IIssueView;

/**
 * Created by funnyzhao .
 */

public class IssuePerImpl implements IIssuePer {
    private IIssueView iIssueView;
    private IssueActivity context;
    public IssuePerImpl(IIssueView iIssueView,IssueActivity context){
        this.iIssueView=iIssueView;
        this.context=context;
    }
    @Override
    public void issueItem() {
        //调用View的UI显示
        iIssueView.getImageHUD().show();
        uploadImage();
    }

    /**
     * 上传图片
     */
    private void uploadImage(){
        String path=iIssueView.getChoosePhoto();
        UploadImage.uploadItemImage(path,this);
    }
    //
    @Override
    public void responseImagePath(String imageNetPath) {
        iIssueView.getImageHUD().dismiss();

        Item item=new Item();
        Item loadItem=iIssueView.getItemInfo();

        item.setItemDescription(loadItem.getItemDescription());
        item.setCategoryName(loadItem.getCategoryName());
        item.setItemAddress(loadItem.getItemAddress());
        item.setPrice(loadItem.getPrice());
        item.setCollectNum("0");
        item.setItemName(loadItem.getItemName());
        item.setItemEffective(true);
        item.setItemImage(imageNetPath);
        item.setUserObjectId(MangoApplication.getUser().getObjectId());
        item.setItemSchool(MangoApplication.getUser().getSchool());
        uploadItem(item);
    }

    /**
     * 上传物品信息
     */
    private void uploadItem(Item item){
        iIssueView.getItemHUD().show();
        //上传物品信息
        RetrofitRequest.addnewItem(item,this);

    }
    @Override
    public void responseItemSuccess() {
        iIssueView.getItemHUD().dismiss();
        iIssueView.showMsg("发布成功");
        //在MangoApplication中设置值，更新首页的最新发布物品，然后情况值
        MangoApplication.setRefreshTag(1);
        context.finish();
    }
}
