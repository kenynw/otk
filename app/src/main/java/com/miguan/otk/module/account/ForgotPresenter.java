package com.miguan.otk.module.account;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.UserModel;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;


/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ForgotPresenter extends Presenter<ForgotActivity> {


    public void sendCaptcha(String mobile) {
        UserModel.getInstance().forgotCaptcha(mobile).subscribe(new ServicesResponse<Boolean>());
    }

    public void changePwd(String mobile, String captcha, String newPwd) {
        UserModel.getInstance().modifyPwd(mobile, captcha, newPwd).subscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                LUtils.getPreferences().edit().putString("token", "").apply();
                getView().finish();
            }
        });
    }

}
