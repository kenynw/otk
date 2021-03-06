package com.miguan.otk.module.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ForgotPresenter.class)
public class ForgotActivity extends ChainBaseActivity<ForgotPresenter> {

    @Bind(R.id.iv_forgot_back)
    ImageView mIvBack;

    @Bind(R.id.et_forgot_mobile)
    EditText mEtMobile;

    @Bind(R.id.et_forgot_new)
    EditText mEtNewPwd;

    @Bind(R.id.et_forgot_confirm)
    EditText mEtConfirm;

    @Bind(R.id.et_forgot_captcha)
    EditText mEtCaptcha;

    @Bind(R.id.btn_forgot_captcha)
    Button mBtnCaptcha;

    @Bind(R.id.btn_forgot_save)
    Button mBtnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_forgot);
        setToolbarTitle(R.string.title_activity_forgot);
        ButterKnife.bind(this);

        mIvBack.setOnClickListener(v -> finish());
        mBtnCaptcha.setOnClickListener(v -> checkCaptcha());
        mBtnSave.setOnClickListener(v -> checkInput());
    }

    private void checkCaptcha() {
        if (mEtMobile.length() != 11) {
            LUtils.toast("请输入正确的手机号码");
            return;
        }
        getPresenter().sendCaptcha(mEtMobile.getText().toString().trim());
    }

    private void checkInput() {
        if (mEtMobile.length() != 11) {
            LUtils.toast("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(mEtConfirm.getText())) {
            LUtils.toast("请再次输入密码");
            return;
        }
        if (!mEtConfirm.getText().toString().equals(mEtNewPwd.getText().toString())) {
            LUtils.toast("再次密码输入不一致");
            return;
        }
        getPresenter().changePwd(
                mEtMobile.getText().toString().trim(),
                mEtNewPwd.getText().toString().trim(),
                mEtCaptcha.getText().toString().trim()
        );
    }

}
