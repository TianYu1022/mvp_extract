package com.tianyu.login_mvp_extract.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseModel {
    public CompositeDisposable compositeDisposable;

    public void onDestroy() {
        //切断所有的 disposable 对象
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void addModel(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable(disposable);
        }
        compositeDisposable.add(disposable);
    }
}
