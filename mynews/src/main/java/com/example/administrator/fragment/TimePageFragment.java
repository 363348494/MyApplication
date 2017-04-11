package com.example.administrator.fragment;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.module.TimePageListItem;
import com.example.administrator.news.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.net.URL;
import java.util.ArrayList;

/**
 * Fragmet需要添加xml布局文件
 */
@ContentView(R.layout.time_page)
public class TimePageFragment extends Fragment {

    @ViewInject(R.id.listview)  // 需要添加listItem
    private ListView listview;

    private ArrayList<TimePageListItem> listdata = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Log.i("jxy", this.getClass() + "---->1: onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.i("jxy", this.getClass() + "---->2: onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Log.i("jxy", this.getClass() + "---->3: onCreateView");
        return x.view().inject(this, inflater, null);
    }

    @Override  // 布局创建完毕,一般此方法用来初始化数据
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("jxy", this.getClass() + "---->4: onViewCreated");
        // 给listView添加数据(创建适配器),不能直接添加,listview.addView();

        // 1: 此处应该先发送http请求,如果进入: onSuccess,则说明数据已经获取
        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/NewServlet");
        x.http().get(params, new Callback.CacheCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("jxy", "http post request success");

                String buffer = result;
                //Log.i("jxy", "buffer = " + buffer);

                // 2: Gson 把string转化为model /map

                //Json的解析类对象
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象

                JsonArray jsonArray = parser.parse(buffer).getAsJsonArray();

                Gson gson = new Gson();

                for (JsonElement item : jsonArray) {
                    //Log.i("jxy", "item = " + item);
                    TimePageListItem timePageListItem = gson.fromJson(item, TimePageListItem.class);
                    //Log.i("jxy", "timePageListItem = " + timePageListItem.toString());
                    listdata.add(timePageListItem);
                }

                //Log.i("jxy", "data[0] = " + listdata.get(0));

                // 3: 根据for循环,对item里面的view控件进行赋值(ImagerView需要单独获取)
                listview.setAdapter(new MyAdapter(getContext(), listdata));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.i("jxy", ex.toString());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //Log.i("jxy", this.getClass() + "---->5: onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("jxy", this.getClass() + "---->6: onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.i("jxy", this.getClass() + "---->7: onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.i("jxy", this.getClass() + "---->8: onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.i("jxy", this.getClass() + "---->9: onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.i("jxy", this.getClass() + "---->10: onDestroy");
    }

    class MyAdapter extends android.widget.BaseAdapter {

        private Context context;

        private LayoutInflater inflater = null;

        private ArrayList<TimePageListItem> data = new ArrayList<>();

        public MyAdapter(Context context, ArrayList<TimePageListItem> listData) {
            this.context = context;
            data = listData;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                // 获得ViewHolder对象
                holder = new ViewHolder();
                // 导入布局并赋值给convertview
                convertView = inflater.inflate(R.layout.time_list_item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv1);
                holder.tv2 = (TextView) convertView.findViewById(R.id.tv2);
                holder.iv = (ImageView) convertView
                        .findViewById(R.id.iv);
                // 为view设置标签
                convertView.setTag(holder);
            } else {
                // 取出holder
                holder = (ViewHolder) convertView.getTag();
            }

            // 设置list中TextView的显示
            holder.tv.setText(data.get(position).getTime());
            holder.tv2.setText(data.get(position).getTitle());
            try {
                URL url = new URL(data.get(position).getImgUrl());
                holder.iv.setImageBitmap(BitmapFactory.decodeStream(url.openStream()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;
        }

        class ViewHolder {
            public TextView tv;
            public TextView tv2;
            public ImageView iv;
        }
    }

}
