package com.aib.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.aib.mgt.R;
import com.aib.mgt.databinding.ActivityMainBinding;
import com.aib.view.fragment.CenterFragment;
import com.aib.view.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        fragments.add(new VideoFragment());
        fragments.add(new CenterFragment());

        binding.bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btn_movie:
                        switchFragment(0);
                        return true;
                    case R.id.btn_center:
                        switchFragment(1);
                        return true;
                }
                return false;
            }
        });

        switchFragment(0);
    }

    private void switchFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == position) {
                if (fragment.isAdded()) {
                    ft.show(fragment);
                } else {
                    ft.add(R.id.fl, fragment);
                }
            } else {
                if (fragment.isAdded()) {
                    ft.hide(fragment);
                }
            }
        }
        ft.commit();
    }

}
