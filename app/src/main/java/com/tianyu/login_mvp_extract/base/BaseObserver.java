package com.tianyu.login_mvp_extract.base;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 封装观察者
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
        // TODO: 2020/6/1 
        Log.e("TAG", "onSubscribe: " + "订阅成功");
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail("onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.e("TAG", "onComplete");
    }

    public abstract void onSuccess(T t);

    public abstract void onFail(String error);

}
