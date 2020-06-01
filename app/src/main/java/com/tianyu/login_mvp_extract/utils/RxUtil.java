package com.tianyu.login_mvp_extract.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 抽取线程切换
 */
public class RxUtil {
    /**
     * <T, T> 转换前的对象类型 | 转换后的对象类型
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxObservableTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
