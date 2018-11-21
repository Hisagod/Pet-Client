package com.aib.view.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.aib.mgt.R;
import com.aib.viewmodel.MainViewModel;

import javax.inject.Inject;


/**
 * 视频
 */
public class VideoFragment extends BaseFragment<ViewDataBinding> {

    @Override
    public int getResId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }
}
