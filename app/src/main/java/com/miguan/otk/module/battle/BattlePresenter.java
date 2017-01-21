package com.miguan.otk.module.battle;

import android.os.Bundle;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.model.BattleModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.services.ServicesResponse;

/**
 * Copyright (c) 2017/1/3. LiaoPeiKun Inc. All rights reserved.
 */

public class BattlePresenter extends BaseDataActivityPresenter<BattleActivity, Battle> {

    private int mBattleID;

    @Override
    protected void onCreate(BattleActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mBattleID = getView().getIntent().getIntExtra("battle_id", 0);
    }

    @Override
    protected void onCreateView(BattleActivity view) {
        super.onCreateView(view);
        setData();
    }

    public void setData() {
        BattleModel.getInstance().getBattleDetail(mBattleID).unsafeSubscribe(getDataSubscriber());
    }



    public void ready() {
        BattleModel.getInstance().ready(mBattleID).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }

}