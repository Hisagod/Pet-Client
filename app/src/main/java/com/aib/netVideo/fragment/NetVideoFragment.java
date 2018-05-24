package com.aib.netVideo.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.aib.base.fragment.BaseFragment;
import com.aib.player.R;
import com.aib.player.databinding.NetaudioPagerBinding;
import com.atguigu.mobileplayer2.adapter.NetAudioPagerAdapter;
import com.atguigu.mobileplayer2.domain.NetAudioPagerData;
import com.atguigu.mobileplayer2.utils.CacheUtils;
import com.atguigu.mobileplayer2.utils.Constants;
import com.atguigu.mobileplayer2.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class NetVideoFragment extends BaseFragment<NetaudioPagerBinding> {
    @Override
    public int getResId() {
        return R.layout.netaudio_pager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        String savaJson = CacheUtils.getString(getContext(), Constants.ALL_RES_URL);
        if (!TextUtils.isEmpty(savaJson)) {
            //解析数据
            processData(savaJson);
        }
        //联网
        getDataFromNet();
    }

    /**
     * 页面的数据
     */
    private List<NetAudioPagerData.ListEntity> datas;

    private NetAudioPagerAdapter adapter;

    private void getDataFromNet() {
        RequestParams params = new RequestParams(Constants.ALL_RES_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("请求数据成功==" + result);
                //保持数据
                CacheUtils.putString(getContext(), Constants.ALL_RES_URL, result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("请求数据失败==" + ex.getMessage());
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

    /**
     * 解析json数据和显示数据
     * 解析数据：1.GsonFormat生成bean对象；2.用gson解析数据
     *
     * @param json
     */
    private void processData(String json) {
        //解析数据
        NetAudioPagerData data = parsedJson(json);
        datas = data.getList();

        if (datas != null && datas.size() > 0) {
            //有数据
            binding.tvNonet.setVisibility(View.GONE);
            //设置适配器
            adapter = new NetAudioPagerAdapter(getContext(), datas);
            binding.listview.setAdapter(adapter);
        } else {
            binding.tvNonet.setText("没有对应的数据...");
            //没有数据
            binding.tvNonet.setVisibility(View.VISIBLE);
        }
        binding.pbLoading.setVisibility(View.GONE);
    }

    /**
     * Gson解析数据
     *
     * @param json
     * @return
     */
    private NetAudioPagerData parsedJson(String json) {
        return new Gson().fromJson(json, NetAudioPagerData.class);
    }
}
