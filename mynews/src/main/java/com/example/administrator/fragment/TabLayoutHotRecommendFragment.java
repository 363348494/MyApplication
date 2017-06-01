package com.example.administrator.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.news.R;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_tablayout_hot_recommend)
public class TabLayoutHotRecommendFragment extends Fragment {

    @ViewInject(R.id.viewPager)
    private ViewPager viewPager;
    @ViewInject(R.id.magic_indicator1)
    private MagicIndicator magicIndicator;

    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager.setAdapter(new MyPageAdapter());

        CircleNavigator circleNavigator = new CircleNavigator(getContext());
        circleNavigator.setCircleCount(3);
        circleNavigator.setCircleColor(Color.LTGRAY);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private class MyPageAdapter extends PagerAdapter {

        private List<View> vList = new ArrayList<View>();

        private int[] imgList = {R.drawable.news_viewpager01, R.drawable.news_viewpager02, R.drawable.news_viewpager03};

        public MyPageAdapter() {
            for (int i = 0; i < imgList.length; i++) {
                ImageView imageView = new ImageView(TabLayoutHotRecommendFragment.this.getActivity());
                imageView.setBackgroundResource(imgList[i]);
                vList.add(imageView);
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = vList.get(position);
            container.addView(view); // 必须添加,否则加载的View将不会被显示
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return vList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
