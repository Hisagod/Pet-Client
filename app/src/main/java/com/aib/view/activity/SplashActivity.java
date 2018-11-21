package com.aib.view.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.aib.loto.R;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;


public class SplashActivity extends BaseActivity<ViewDataBinding> {
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
                ToastUtils.showShort("拒绝授权");
                finish();
            }
        }).request();
    }
}
