package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.presenter.INewstPer;

/**
 * Created by funnyzhao .
 *  最新
 */

public interface INewstModel {
    /**
     * 从服务器加载数据
     * @param iNewstPer
     */
    void  loadItemsNet(INewstPer iNewstPer);
}
