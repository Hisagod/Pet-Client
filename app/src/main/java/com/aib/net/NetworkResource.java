package com.aib.net;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkResource<RequestType, ResultType> {
    private MediatorLiveData<Resource<ResultType>> data = new MediatorLiveData<>();

    public NetworkResource() {
        data.setValue(new Resource<ResultType>(null, Status.LOADING, null));
        if (NetworkUtils.isConnected()) {
            createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(new Consumer<RequestType>() {
                        @Override
                        public void accept(RequestType requestType) throws Exception {
                            saveData(requestType);
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RequestType>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(RequestType requestType) {
                            ResultType dbResource = loadFromDb();
                            data.setValue(new Resource<ResultType>(null, Status.SUCCESS, dbResource));
                        }

                        @Override
                        public void onError(Throwable e) {
                            data.setValue(new Resource<ResultType>(e.getMessage(), Status.ERROR, null));
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            ResultType dbReource = loadFromDb();
            if (dbReource == null) {
                data.setValue(new Resource<ResultType>("无网络", Status.ERROR, null));
            } else {
                data.setValue(new Resource<ResultType>(null, Status.SUCCESS, dbReource));
            }
        }
    }

    public LiveData<Resource<ResultType>> asLiveData() {
        return data;
    }

    public abstract ResultType loadFromDb();

    public abstract Observable<RequestType> createCall();

    public abstract void saveData(RequestType data);


}
