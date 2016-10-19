package me.funnyzhao.mangostreet.presenter;

/**
 * Created by funnyzhao .
 * 编辑页面控制层
 */

public interface IEditorUserPer {
    /**
     * 保存数据
     */
    void saveData();

    /**
     * 上传图片
     * @param imagePath
     */
    void uploadImage(String imagePath);

    /**
     * 获取图片url
     * @param imageUrl
     */
    void showImageUrl(String imageUrl);

    /**
     * 更新信息完成
     */
    void updateUserOk();
}
