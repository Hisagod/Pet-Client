package com.aib.ui.movie.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.aib.net.Resource;
import com.aib.ui.movie.entity.VideoEntity;
import com.aib.ui.movie.repository.MainRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private final MainRepository rep;

    @Inject
    public MainViewModel(MainRepository rep) {
        this.rep = rep;
    }

    public LiveData<Resource<VideoEntity>> getJson() {
       return rep.getJson();
    }
}
