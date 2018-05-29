package com.aib.ui.movie.repository;


import android.arch.lifecycle.LiveData;

import com.aib.net.ApiService;
import com.aib.net.NetworkResource;
import com.aib.net.Resource;
import com.aib.ui.movie.entity.VideoEntity;
import com.blankj.utilcode.util.CacheUtils;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MainRepository {
    private final ApiService apiService;

    @Inject
    public MainRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<Resource<VideoEntity>> getJson() {
        return new NetworkResource<VideoEntity, VideoEntity>() {

            @Override
            public VideoEntity loadFromDb() {
                VideoEntity entity = (VideoEntity) CacheUtils.getInstance().getSerializable("videoEntity");
                return entity;
            }

            @Override
            public Observable<VideoEntity> createCall() {
                return apiService.VIDEO_LIST();
            }

            @Override
            public void saveData(VideoEntity data) {
                CacheUtils.getInstance().put("videoEntity", data);
            }


        }.asLiveData();
    }

}
