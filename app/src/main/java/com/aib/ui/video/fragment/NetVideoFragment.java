package com.aib.ui.video.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import com.aib.base.fragment.BaseFragment;
import com.aib.player.R;
import com.aib.player.databinding.NetvideoPagerBinding;
import com.atguigu.mobileplayer2.activity.SystemVideoPlayer;
import com.atguigu.mobileplayer2.adapter.NetVideoPagerAdapter;
import com.atguigu.mobileplayer2.domain.MediaItem;
import com.atguigu.mobileplayer2.utils.CacheUtils;
import com.atguigu.mobileplayer2.utils.Constants;
import com.atguigu.mobileplayer2.utils.LogUtil;
import com.atguigu.mobileplayer2.view.XListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NetVideoFragment extends BaseFragment<NetvideoPagerBinding> {

    /**
     * 装数据集合
     */
    private ArrayList<MediaItem> mediaItems;

    private NetVideoPagerAdapter adapter;

    /**
     * 是否已经加载更多了
     */
    private boolean isLoadMore = false;

    @Override
    public int getResId() {
        return R.layout.netvideo_pager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        binding.listview.setOnItemClickListener(new MyOnItemClickListener());
        binding.listview.setPullLoadEnable(true);
        binding.listview.setXListViewListener(new MyIXListViewListener());

        String saveJson = CacheUtils.getString(getContext(), Constants.NET_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        getDataFromNet();
    }


    class MyIXListViewListener implements XListView.IXListViewListener {
        @Override
        public void onRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {
            getMoreDataFromNet();
        }
    }

    private void getMoreDataFromNet() {
        //联网
        //视频内容
        RequestParams params = new RequestParams(Constants.NET_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功==" + result);
                isLoadMore = true;
                //主线程
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败==" + ex.getMessage());
                isLoadMore = false;
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
                isLoadMore = false;
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
                isLoadMore = false;
            }
        });
    }


    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //3.传递列表数据-对象-序列化
            Intent intent = new Intent(getContext(), SystemVideoPlayer.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("videolist", mediaItems);
            intent.putExtras(bundle);
            intent.putExtra("position", position - 1);
            getActivity().startActivity(intent);
        }
    }

    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.NET_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功==" + result);
                //缓存数据
                CacheUtils.putString(getContext(), Constants.NET_URL, result);
                //主线程
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败==" + ex.getMessage());
                showData();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }

    private void processData(String json) {

        if (!isLoadMore) {
            mediaItems = parseJson(json);
            showData();


        } else {
            //加载更多
            //要把得到更多的数据，添加到原来的集合中
//            ArrayList<MediaItem> moreDatas = parseJson(json);
            isLoadMore = false;
            mediaItems.addAll(parseJson(json));
            //刷新适配器
            adapter.notifyDataSetChanged();
            onLoad();


        }


    }

    private void showData() {
        //设置适配器
        if (mediaItems != null && mediaItems.size() > 0) {
            //有数据
            //设置适配器
            adapter = new NetVideoPagerAdapter(getContext(), mediaItems);
            binding.listview.setAdapter(adapter);
            onLoad();
            //把文本隐藏
            binding.tvNonet.setVisibility(View.GONE);
        } else {
            //没有数据
            //文本显示
            binding.tvNonet.setVisibility(View.VISIBLE);
        }


        //ProgressBar隐藏
        binding.pbLoading.setVisibility(View.GONE);
    }


    private void onLoad() {
        binding.listview.stopRefresh();
        binding.listview.stopLoadMore();
        binding.listview.setRefreshTime("更新时间:" + getSysteTime());
    }

    /**
     * 得到系统时间
     *
     * @return
     */
    public String getSysteTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 解决json数据：
     * 1.用系统接口解析json数据
     * 2.使用第三方解决工具（Gson,fastjson）
     *
     * @param json
     * @return
     */
    private ArrayList<MediaItem> parseJson(String json) {
        ArrayList<MediaItem> mediaItems = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.optJSONArray("trailers");
            if (jsonArray != null && jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);

                    if (jsonObjectItem != null) {

                        MediaItem mediaItem = new MediaItem();


                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setName(movieName);

                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setDesc(videoTitle);

                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setImageUrl(imageUrl);

                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setData(hightUrl);

                        //把数据添加到集合
                        mediaItems.add(mediaItem);
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mediaItems;
    }

}
