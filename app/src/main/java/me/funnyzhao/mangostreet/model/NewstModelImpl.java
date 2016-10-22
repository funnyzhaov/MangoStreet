package me.funnyzhao.mangostreet.model;

import me.funnyzhao.mangostreet.presenter.INewstPer;
import me.funnyzhao.mangostreet.util.request.RetrofitRequest;

/**
 * Created by funnyzhao .
 */

public class NewstModelImpl implements INewstModel {
    @Override
    public void loadItemsNet(INewstPer iNewstPer) {
        RetrofitRequest.getNewItems(iNewstPer);
    }
}
