package com.aib.ui.splash;

import android.os.Bundle;

import com.aib.base.activity.BaseActivity;
import com.aib.net.ApiService;
import com.aib.player.R;
import com.aib.player.databinding.ActivitySplashBinding;
import com.aib.ui.other.activity.MainActivity;
import com.atguigu.mobileplayer2.utils.LogUtil;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
    @Inject
    ApiService api;
    private String[] permissions = {
            "android.permission.READ_EXTERNAL_STORAGE"
    };

    @Override
    public int getResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        requestPermission();

        LogUtil.e(api.toString());
    }

    private void requestPermission() {
        PermissionUtils.permission(permissions).callback(new PermissionUtils.SimpleCallback() {
            @Override
            public void onGranted() {
                ActivityUtils.startActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onDenied() {

            }
        }).request();
    }
}
