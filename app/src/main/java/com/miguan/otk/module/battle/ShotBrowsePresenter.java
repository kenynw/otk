package com.miguan.otk.module.battle;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.dsk.chain.bijection.Presenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.ImageModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.utils.LUtils;

import java.io.File;

import rx.Observable;
import rx.functions.Func1;

/**
 * Copyright (c) 2017/2/6. LiaoPeiKun Inc. All rights reserved.
 */

public class ShotBrowsePresenter extends Presenter<ShotBrowseActivity> {

    private int mBattleID;

    private String mKind;

    private Uri mUri;

    @Override
    protected void onCreate(ShotBrowseActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattleID = getView().getIntent().getIntExtra("battle_id", 0);
        mKind = getView().getIntent().getStringExtra("kind");
        mUri = getView().getIntent().getParcelableExtra("uri");
    }

    public void upload() {
        ImageModel.getInstance().uploadImageAsync(new File(mUri.getPath()))
                .flatMap(new Func1<String, Observable<Battle>>() {
                    @Override
                    public Observable<Battle> call(String s) {
                        return BattleModel.getInstance().upload(s, mBattleID, mKind);
                    }
                })
                .unsafeSubscribe(new ServicesResponse<Battle>() {
                    @Override
                    public void onNext(Battle result) {
                        LUtils.toast("上传成功");
                        getView().setResult(Activity.RESULT_OK);
                        getView().finish();
                    }
                });
    }

}
