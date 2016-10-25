package me.funnyzhao.mangostreet.presenter;

import java.util.List;

import me.funnyzhao.mangostreet.HomeActivity;
import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 * 推荐商品
 * tips：由于没有办法控制服务器程序，就在本地根据物品被收藏数的高低来进行推荐。
 */

public interface IRecommendPer extends BasePer{
    /**
     * 加载推荐数据
     */
    void loadItems();
    /**
     * 响应请求数据
     * @param itemList
     */
    void responseItems(List<Item> itemList);
    /**
     * 进入物品详情页
     * @param context
     * @param item
     */
    void toShowDetailsPage(HomeActivity context, Item item);
}
