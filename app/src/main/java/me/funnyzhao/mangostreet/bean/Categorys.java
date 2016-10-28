package me.funnyzhao.mangostreet.bean;

/**
 * Created by funnyzhao .
 * 分类
 */

public class Categorys {
    private static String categorys[]={"电子外设"
            , "手机"
            ,"电脑"
            ,"校园代步"
            ,"数码"
            ,"电器"
            ,"运动健身"
            ,"衣物伞帽"
            ,"大学必备"};

    /**
     * 获取分类列表
     * @return
     */
    public static String[] getCategorys(){
        return categorys;
    }
}
