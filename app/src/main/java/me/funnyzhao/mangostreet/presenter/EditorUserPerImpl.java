package me.funnyzhao.mangostreet.presenter;

import android.os.Handler;
import android.os.Looper;

import me.funnyzhao.mangostreet.MangoApplication;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;
import me.funnyzhao.mangostreet.util.request.UploadImage;
import me.funnyzhao.mangostreet.view.IEditorUserView;

/**
 * Created by funnyzhao .
 */

public class EditorUserPerImpl implements IEditorUserPer {
    private IEditorUserView iEditorUserView;
    private Handler handler=new Handler(Looper.getMainLooper());
    private _User user;
    public EditorUserPerImpl(IEditorUserView iEditorUserView){
        this.iEditorUserView=iEditorUserView;
    }
    @Override
    public void saveData() {
        iEditorUserView.showDoneProgress();
        user=iEditorUserView.getNewData();
        MangoApplication.getUser().setDepartment(user.getDepartment());
        MangoApplication.getUser().setMajor(user.getMajor());
        MangoApplication.getUser().setStarttime(user.getStarttime());
        MangoApplication.getUser().setImageurl(user.getImageurl());
        if (user!=null){
            updateData();
        }
    }

    @Override
    public void uploadImage(String imagePath) {
        iEditorUserView.showProgress();
        UploadImage.upload(imagePath,this);
    }

    @Override
    public void showImageUrl(String imageUrl) {
        iEditorUserView.setImageUrl(imageUrl);
        iEditorUserView.stopProgress();
    }

    @Override
    public void updateUserOk() {
        iEditorUserView.stopDoneProgress();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iEditorUserView.backUserActivity();
            }
        },1000);
    }

    /**
     * 更新数据库中的信息
     */
    private void updateData(){
        RetrofitRequest.toUpdateUser(user,this);
    }
}
