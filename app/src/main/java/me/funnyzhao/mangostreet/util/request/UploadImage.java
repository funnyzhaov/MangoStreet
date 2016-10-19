package me.funnyzhao.mangostreet.util.request;

import com.orhanobut.logger.Logger;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;
import me.funnyzhao.mangostreet.presenter.IEditorUserPer;

/**
 * Created by funnyzhao .
 * 上传图片
 */

public class UploadImage {
    public static void upload(String imagePath, final IEditorUserPer iEditorUserPer){
        final BmobFile bmobFile = new BmobFile(new File(imagePath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    iEditorUserPer.showImageUrl(bmobFile.getFileUrl());
                    Logger.d("上传文件成功"+bmobFile.getFileUrl());
                }else{
                    Logger.d("上传文件成功"+e.getMessage());
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }
}
