package com.tianyu.login_mvp_extract.base;

public interface BaseView<T> {
    void onSuccess(T t);

    void onFail(String error);
}
