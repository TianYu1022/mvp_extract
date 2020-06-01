package com.tianyu.login_mvp_extract.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tianyu.login_mvp_extract.R;
import com.tianyu.login_mvp_extract.base.BaseActivity;
import com.tianyu.login_mvp_extract.bean.LoginBean;
import com.tianyu.login_mvp_extract.presenter.ImpLoginPresenter;
import com.tianyu.login_mvp_extract.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity<ImpLoginPresenter, LoginView> implements LoginView {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pass)
    EditText etPass;

    @Override
    protected LoginView initMvpView() {
        return this;
    }

    @Override
    protected ImpLoginPresenter initMvpPresenter() {
        return new ImpLoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(LoginBean loginBean) {
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = etName.getText().toString().trim();
                String pwd = etPass.getText().toString().trim();
                mPresenter.login(name,pwd);
                break;
            case R.id.btn_register:
                toast("注册");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}