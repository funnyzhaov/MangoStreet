package me.funnyzhao.mangostreet.bean.success;

import java.util.Arrays;

import me.funnyzhao.mangostreet.bean.Item;

/**
 * Created by funnyzhao .
 * 物品查询信息响应体
 */

public class ItemResultBody {
    private Item results[];

    public Item[] getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ItemResultBody{" +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
