package com.tianyu.login_mvp_extract.base;

public interface BaseCallback<T> {
    void onSuccess(T t);

    void onFail(String error);
}
