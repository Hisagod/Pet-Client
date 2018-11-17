package com.aib.view.fragment;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;

import com.aib.mgt.R;
import com.aib.mgt.databinding.VideoPagerBinding;
import com.atguigu.mobileplayer2.activity.SystemVideoPlayer;
import com.atguigu.mobileplayer2.adapter.VideoPagerAdapter;
import com.atguigu.mobileplayer2.domain.MediaItem;

import java.util.ArrayList;

/**
 * 本地视频
 */
public class LocalVideoFragment extends BaseFragment<VideoPagerBinding> {
    private VideoPagerAdapter videoPagerAdapter;

    /**
     * 装数据集合
     */
    private ArrayList<MediaItem> mediaItems;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mediaItems != null && mediaItems.size() > 0) {
                //有数据
                //设置适配器
                videoPagerAdapter = new VideoPagerAdapter(getContext(), mediaItems, true);
                binding.listview.setAdapter(videoPagerAdapter);
                //把文本隐藏
                binding.tvNomedia.setVisibility(View.GONE);
            } else {
                //没有数据
                //文本显示
                binding.tvNomedia.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public int getResId() {
        return R.layout.video_pager;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //设置ListView的Item的点击事件
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), SystemVideoPlayer.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("videolist", mediaItems);
                intent.putExtras(bundle);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        getDataFromLocal();
    }


    /**
     * 从本地的sdcard得到数据
     * //1.遍历sdcard,后缀名
     * //2.从内容提供者里面获取视频
     * //3.如果是6.0的系统，动态获取读取sdcard的权限
     */
    private void getDataFromLocal() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mediaItems = new ArrayList<>();
                ContentResolver resolver = getContext().getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                        MediaStore.Video.Media.DURATION,//视频总时长
                        MediaStore.Video.Media.SIZE,//视频的文件大小
                        MediaStore.Video.Media.DATA,//视频的绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲的演唱者

                };
                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {

                        MediaItem mediaItem = new MediaItem();

                        mediaItems.add(mediaItem);//写在上面

                        String name = cursor.getString(0);//视频的名称
                        mediaItem.setName(name);

                        long duration = cursor.getLong(1);//视频的时长
                        mediaItem.setDuration(duration);

                        long size = cursor.getLong(2);//视频的文件大小
                        mediaItem.setSize(size);

                        String data = cursor.getString(3);//视频的播放地址
                        mediaItem.setData(data);

                        String artist = cursor.getString(4);//艺术家
                        mediaItem.setArtist(artist);
                    }
                    cursor.close();
                }
                //Handler发消息
                handler.sendEmptyMessage(10);
            }
        }.start();

    }
}
