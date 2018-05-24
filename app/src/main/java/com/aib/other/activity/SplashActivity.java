package com.aib.other.activity;

import android.os.Bundle;

import com.aib.base.activity.BaseActivity;
import com.aib.player.R;
import com.aib.player.databinding.ActivitySplashBinding;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;


public class SplashActivity extends BaseActivity<ActivitySplashBinding> {
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
