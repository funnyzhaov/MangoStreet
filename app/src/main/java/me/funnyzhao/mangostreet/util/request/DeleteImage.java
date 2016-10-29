package me.funnyzhao.mangostreet.util.request;

import com.orhanobut.logger.Logger;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by funnyzhao .
 * Bmob删除图片
 */

public class DeleteImage {
    /**
     * 图片uril
     * @param url
     */
    public static void deleteMyissue(String url){
        BmobFile file = new BmobFile();
        file.setUrl(url);
        file.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    Logger.d("文件删除成功");
                }else{
                    Logger.d("文件删除失败："+e.getErrorCode()+","+e.getMessage());
                }
            }
        });
    }
}
