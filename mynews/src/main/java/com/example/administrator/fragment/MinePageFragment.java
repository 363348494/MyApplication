package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.administrator.news.R;

public class MinePageFragment extends Fragment {

    private WebView webView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mine_page, container, false);
        webView = (WebView) view.findViewById(R.id.webview);
        WebSettings settings = webView.getSettings();
        // 开启被加载页面的JS功能,否则JS代码不会被执行
        settings.setJavaScriptEnabled(true);
        // 可以加载远程或者本地资源
        webView.loadUrl("file:///android_asset/index.html");
        return view;
    }

}
