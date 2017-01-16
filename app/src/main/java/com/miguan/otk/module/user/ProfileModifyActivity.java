package com.miguan.otk.module.user;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dsk.chain.bijection.ChainBaseActivity;
import com.dsk.chain.bijection.RequiresPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.User;
import com.sgun.utils.LUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

@RequiresPresenter(ProfileModifyPresenter.class)
public class ProfileModifyActivity extends ChainBaseActivity<ProfileModifyPresenter> {

    @Bind(R.id.et_profile_modify_input)
    EditText mEtInput;

    @Bind(R.id.btn_profile_modify_submit)
    Button mBtnSubmit;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_profile_modify);
        ButterKnife.bind(this);

        mUser = getIntent().getParcelableExtra("user");
        setLayout();
    }

    private void setLayout() {
        switch (getIntent().getIntExtra("type", 0)) {
            case 0 :
                setToolbarTitle("QQ");
                if (!TextUtils.isEmpty(mUser.getQq())) mEtInput.setText(mUser.getQq());
                break;
            case 1 :
                setToolbarTitle("邮箱");
                if (!TextUtils.isEmpty(mUser.getEmail())) mEtInput.setText(mUser.getEmail());
                break;
            case 2 :
                setToolbarTitle("签名");
                if (!TextUtils.isEmpty(mUser.getSign())) mEtInput.setText(mUser.getSign());
                mEtInput.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LUtils.dp2px(128)));
                break;
        }
    }

}