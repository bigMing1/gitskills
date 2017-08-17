package com.example.layoutdemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by ljm on 2017/8/17.
 */

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ArrayList<View> pageview;


    private ImageView imageView;
    private ImageView[] imageViews;
    //包裹点点的LinearLayout
    private ViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题栏
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //查找布局文件用LayoutInflater.inflate
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.item01, null);
        View view2 = inflater.inflate(R.layout.item02, null);
        View view3 = inflater.inflate(R.layout.item03, null);

        //将view装入数组
        pageview = new ArrayList<View>();
        pageview.add(view1);
        pageview.add(view2);
        pageview.add(view3);


        group = (ViewGroup) findViewById(R.id.viewGroup);

        //有多少张图就有多少个点点
        imageViews = new ImageView[pageview.size()];
        for (int i = 0; i < pageview.size(); i++) {
            imageView = new ImageView(MainActivity.this);
            //imageView.setLayoutParams(new LayoutParams(20,20));
            imageView.setPadding(20, 0, 20, 0);
            imageViews[i] = imageView;

            //默认第一张图显示为选中状态
            if (i == 0) {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            group.addView(imageViews[i]);
        }


        //绑定适配器
        viewPager.setAdapter(mPagerAdapter);
        //绑定监听事件
        viewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    //数据适配器
    PagerAdapter mPagerAdapter = new PagerAdapter() {

        @Override
        //获取当前窗体界面数
        public int getCount() {
            // TODO Auto-generated method stub
            return pageview.size();
        }

        @Override
        //断是否由对象生成界面
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        //是从ViewGroup中移出当前View
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(pageview.get(arg1));
        }

        @Override
        //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(pageview.get(arg1));
            return pageview.get(arg1);
        }


    };

    //pageView监听器
    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        //如果切换了，就把当前的点点设置为选中背景，其他设置未选中背景
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
                }
            }

        }

    }
}
