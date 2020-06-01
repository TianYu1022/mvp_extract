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
    private Disposable mDisposable;
    private BaseModel mModel;

    public BaseObserver(BaseModel mModel) {
        this.mModel = mModel;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        mModel.addModel(mDisposable);
        Log.e("TAG", "onSubscribe: " + "订阅成功");
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
        //断开订阅
        mDisposable.dispose();
        mModel.compositeDisposable.remove(mDisposable);
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
