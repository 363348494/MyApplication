package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class TvPageFragment extends Fragment {

    private BridgeWebView bridgeWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tv_page, container, false);

        bridgeWebView = (BridgeWebView)view.findViewById(R.id.bridgeWebView);

        bridgeWebView.loadUrl("file:///android_asset/refresh.html");

        bridgeWebView.registerHandler("getServerData", new BridgeHandler() {

            @Override  // data： 从页面传入的数据
            public void handler(String keyword, final CallBackFunction function) {
                Log.i("jxy", "keyword:" + keyword);
                RequestParams params = new RequestParams("http://www.jxy-edu.com/AjaxServlet");
                params.addQueryStringParameter("keyword",keyword);
                params.addQueryStringParameter("currentPage","1");
                params.addQueryStringParameter("size","10");
                // 需要开启网络权限
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("jxy", "返回的数据为:" + result);
                        // 调用xutils3的http框架,访问远程数据并且返回结果
                        function.onCallBack(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(Callback.CancelledException cex) {
                        Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {

                    }
                });

            }

        });

        return view;
    }
}
