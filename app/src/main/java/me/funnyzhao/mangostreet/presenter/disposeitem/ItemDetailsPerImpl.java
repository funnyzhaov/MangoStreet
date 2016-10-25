package me.funnyzhao.mangostreet.presenter.disposeitem;

import me.funnyzhao.mangostreet.bean.Item;
import me.funnyzhao.mangostreet.bean._User;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;
import me.funnyzhao.mangostreet.view.dmview.IItemDetailsView;

/**
 * Created by funnyzhao .
 */

public class ItemDetailsPerImpl implements IItemDetailsPer {
    public IItemDetailsView iItemDetailsView;
    public ItemDetailsPerImpl(IItemDetailsView iItemDetailsView){
        this.iItemDetailsView=iItemDetailsView;
    }
    @Override
    public void showNoNetWork() {
        iItemDetailsView.showNetWorkInfo();
    }

    @Override
    public void showRequestInfo(String info) {
    }

    @Override
    public void loadUserInfo() {
        Item item=iItemDetailsView.getItem();
        String userObjectId=item.getUserObjectId();
        RetrofitRequest.getUserInfoById(userObjectId,this);
    }

    @Override
    public void loadSuccess(_User user) {
        iItemDetailsView.setUserInfo(user);
    }

    @Override
    public void loadFailure() {
        iItemDetailsView.showMsg("网络无连接");
    }
}
