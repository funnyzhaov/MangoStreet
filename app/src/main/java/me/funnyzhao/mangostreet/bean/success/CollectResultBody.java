package me.funnyzhao.mangostreet.bean.success;

import java.util.Arrays;

import me.funnyzhao.mangostreet.bean.Collect;

/**
 * Created by funnyzhao .
 * 收藏信息响应体
 */

public class CollectResultBody {
    private Collect results[];

    public Collect[] getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ItemResultBody{" +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
