package com.example.lenovo.jiandan.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lenovo.jiandan.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/3/19.
 */
public class MyViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener{

    private Context mContext;
    private ViewPager viewPager;
    private ArrayList<String> urls = new ArrayList<String>();
    private LinearLayout ll;

    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        urls.add("1");
        urls.add("1");
        urls.add("1");
        urls.add("1");
        urls.add("1");

        init();
    }

    private void init() {
        //初始化viewpager
        viewPager = new ViewPager(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewPager.setLayoutParams(params);
        viewPager.setAdapter(new MyPagerAdapter());
        //初始化标识器
        ll = new LinearLayout(mContext);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(CENTER_IN_PARENT);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT)
        params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params1.bottomMargin = 5;
        ll.setLayoutParams(params1);

        addView(viewPager);
        addView(ll);

        createIndicator();

    }

    private void createIndicator() {
        if(urls.size()>1){
            for (int i=0;i<urls.size();i++){
                View pointView = new View(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                if(i!=urls.size()){
                    params.rightMargin = 3;
                }
                pointView.setLayoutParams(params);
                pointView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.indicator));
                pointView.setEnabled(false);
                ll.addView(pointView);
            }
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
         for (int i=0;i<urls.size();i++){
             if(position ==i){
                 ll.getChildAt(position).setEnabled(true);
             }else{
                 ll.getChildAt(position).setEnabled(false);
             }
         }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    public class MyPagerAdapter extends PagerAdapter{

        public MyPagerAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
        }

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
