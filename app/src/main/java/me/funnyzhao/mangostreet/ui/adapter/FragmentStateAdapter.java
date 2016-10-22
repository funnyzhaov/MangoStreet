package me.funnyzhao.mangostreet.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by funnyzhao .
 * viewpager的Fragment适配器
 */

public class FragmentStateAdapter extends FragmentStatePagerAdapter {
    //Fragments
    private List<Fragment> fragmentLists;
    //标题
    private String[] tabTitleArray;

    public FragmentStateAdapter(FragmentManager fm,List<Fragment> list,String[] tabTitleArray) {
        super(fm);
        this.fragmentLists=list;
        this.tabTitleArray=tabTitleArray;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists.size();
    }
    //与TabLayout配合使用需要复写的方法
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleArray[position % tabTitleArray.length];
    }
}
