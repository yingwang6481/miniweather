package cn.edu.pku.wangying.miniweather;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * Created by wangying on 2016/11/29.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<View> views;
    private Context context;

    public ViewPagerAdapter(List<View> views,Context context){
        this.views=views;
        this.context=context;
    }
    @Override
    public int getCount(){
        return views.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container,int position){
        View view=views.get(position);

        container.addView(view);
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    public boolean isViewFromObject(View view, Object o){

        return (view==o);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
