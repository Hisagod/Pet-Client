package com.aib.ui.movie.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.aib.base.fragment.BaseFragment;
import com.aib.net.Resource;
import com.aib.player.R;
import com.aib.player.databinding.FragmentVideoBinding;
import com.aib.ui.movie.viewmodel.MainViewModel;
import com.aib.ui.movie.entity.VideoEntity;

import javax.inject.Inject;


/**
 * 视频
 */
public class VideoFragment extends BaseFragment<FragmentVideoBinding> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    public int getResId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        MainViewModel vm = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        vm.getJson().observe(this, new Observer<Resource<VideoEntity>>() {
            @Override
            public void onChanged(@Nullable Resource<VideoEntity> videoEntityResource) {
                binding.setData(videoEntityResource);
            }
        });

    }
}
