package com.aib.base.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity {

    public D binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getResId());

        initData(savedInstanceState);
    }

    public abstract int getResId();

    public abstract void initData(Bundle savedInstanceState);
}
