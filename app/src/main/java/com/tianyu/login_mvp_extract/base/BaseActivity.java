package com.tianyu.login_mvp_extract.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 封装activity
 */
public abstract class BaseActivity<P extends BasePresenter, V extends BaseView> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //抽取MVP
        mPresenter = initMvpPresenter();
        if (mPresenter != null) {
            mPresenter.setmView(initMvpView());
        }

        initView();
        initData();
        initListener();
    }

    protected abstract V initMvpView();

    protected abstract P initMvpPresenter();

    private void initListener() {
    }

    private void initData() {
    }

    private void initView() {
    }

    protected abstract int getLayoutId();

    public void toast(String toastMsg){
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}
