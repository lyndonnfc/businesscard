package com.nfc.lyndon.businesscard.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nfc.lyndon.businesscard.R;
import com.nfc.lyndon.businesscard.base.MvpActivity;
import com.nfc.lyndon.businesscard.contract.LoginContract;
import com.nfc.lyndon.businesscard.model.LoginModel;
import com.nfc.lyndon.businesscard.presenter.LoginPresenter;
import com.nfc.lyndon.businesscard.util.StringUtils;
import com.nfc.lyndon.businesscard.util.ToastUtils;
import com.nfc.lyndon.businesscard.util.WeakHandler;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 */
public class LoginActivity extends MvpActivity<LoginPresenter, LoginModel> implements LoginContract.LoginView{

    private static final int HANDLER_REFRESH_TIME = 1;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private int time = 60;

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected LoginModel initModel() {
        return new LoginModel();
    }

    @OnClick({R.id.tv_code, R.id.btn_login})
    public void onViewClicked(View view) {
        String phone = etPhone.getText().toString().trim();
        String code = etCode.getText().toString().trim();
        switch (view.getId()) {
            case R.id.tv_code:
                if (!StringUtils.isMobileNo(phone)){
                    ToastUtils.toastShort("请输入正确的手机号");
                    return;
                }
                mPresenter.requestCode(phone);
                break;
            case R.id.btn_login:
                if (!StringUtils.isMobileNo(phone)){
                    ToastUtils.toastShort("请输入正确的手机号");
                    return;
                } else if (TextUtils.isEmpty(code)){
                    ToastUtils.toastShort("请输入验证码");
                    return;
                }
                mPresenter.login(code, phone);
                break;
        }
    }

    @Override
    public void startCountDown() {
        tvCode.setEnabled(false);
        mHandler.postDelayed(runnable, 1000);
    }

    @Override
    public void endCountDown() {
        mHandler.removeCallbacks(runnable);
        tvCode.setEnabled(true);
        tvCode.setText("重新获取");
        time = 60;
    }

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.toastShort(msg);
            }
        });
    }

    @Override
    public void failed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.toastShort(msg);
                endCountDown();
            }
        });
    }

    @Override
    public void toMain() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
        finish();
    }

    WeakHandler mHandler = new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case HANDLER_REFRESH_TIME:
                    tvCode.setText(StringUtils.getReString(mContext, R.string.count_down, time));
                    if (time == 0){
                        endCountDown();
                    }
                    break;
            }
            return false;
        }
    });

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if (time >= 0) {
                mHandler.sendEmptyMessage(HANDLER_REFRESH_TIME);
                mHandler.postDelayed(runnable, 1000);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(runnable);
    }
}
