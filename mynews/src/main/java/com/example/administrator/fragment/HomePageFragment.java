package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.news.R;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {

    private TabLayout tab_FindFragment_title;                            //定义TabLayout
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private TabLayoutHotRecommendFragment hotRecommendFragment;                 //热门推荐fragment
    private TabLayoutNewsLiveFragment newsLiveFragment;                         //要闻直播fragment
    private TabLayoutNewsInternationalFragment newsInternationalFragment;       //国际新闻fragment
    private TabLayoutHotSocialTodayFragment hotSocialTodayFragment;             //今日社会fragment
    private TabLayoutFinancialReviewFragment financialReviewFragment;           //财经评论fragment
    private TabLayoutGlobalMilitaryFragment globalMilitaryFragment;             //世界军事fragment

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override  // 每个Fragment都有自己的XML配置文件
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.i("jxy",this.getClass() + "---->2: onCreateView");

        View view = inflater.inflate(R.layout.home_page, container, false);

        initControls(view);

        return view;

//        return x.view().inject(this, inflater, null);
//      return View.inflate(this.getActivity(), R.layout.home_page,null);

    }

    private void initControls(View view) {

        tab_FindFragment_title = (TabLayout) view.findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager) view.findViewById(R.id.vp_FindFragment_pager);

        //初始化各fragment
        hotRecommendFragment = new TabLayoutHotRecommendFragment();
        newsLiveFragment = new TabLayoutNewsLiveFragment();
        newsInternationalFragment = new TabLayoutNewsInternationalFragment();
        hotSocialTodayFragment = new TabLayoutHotSocialTodayFragment();
        financialReviewFragment = new TabLayoutFinancialReviewFragment();
        globalMilitaryFragment = new TabLayoutGlobalMilitaryFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotRecommendFragment);
        list_fragment.add(newsLiveFragment);
        list_fragment.add(newsInternationalFragment);
        list_fragment.add(hotSocialTodayFragment);
        list_fragment.add(financialReviewFragment);
        list_fragment.add(globalMilitaryFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("要闻直播");
        list_title.add("国际新闻");
        list_title.add("今日社会");
        list_title.add("财经评论");
        list_title.add("世界军事");

        //设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_SCROLLABLE);

        //为TabLayout添加tab名称
        for (int i = 0; i <= 5; i++) {
            tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(i)));
        }

        fAdapter = new TabLayoutAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }

}
