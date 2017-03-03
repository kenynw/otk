package com.miguan.otk.module.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dsk.chain.expansion.data.BaseDataActivityPresenter;
import com.miguan.otk.R;
import com.miguan.otk.model.MatchModel;
import com.miguan.otk.model.bean.Battle;
import com.miguan.otk.model.bean.Match;
import com.miguan.otk.model.services.ServicesResponse;
import com.miguan.otk.module.battle.BattleActivity;
import com.miguan.otk.wxapi.ShareCallback;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (c) 2016/11/25. LiaoPeiKun Inc. All rights reserved.
 */
public class MatchDetailPresenter extends BaseDataActivityPresenter<MatchDetailActivity, Match> {

    private int mMatchID;

    private Match mMatch;

    @Override
    protected void onCreate(MatchDetailActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mMatchID = getView().getIntent().getIntExtra("match_id", 0);
    }

    @Override
    protected void onCreateView(MatchDetailActivity view) {
        super.onCreateView(view);
        loadData();
    }

    public void loadData() {
        MatchModel.getInstance().getMatchDetail(mMatchID)
                .unsafeSubscribe(new ServicesResponse<Match>() {
                    @Override
                    public void onNext(Match match) {
                        getView().setData(match);
                        mMatch = match;
                    }
                });
    }

    public void share() {
        if (mMatch == null) return;

        UMWeb umWeb = new UMWeb(mMatch.getShare_url());
        umWeb.setTitle(mMatch.getTitle());
        umWeb.setThumb(new UMImage(getView(), R.mipmap.ic_launcher));
        umWeb.setDescription(mMatch.getTitle() + "火热进行中！快上车，没时间解释了！");
        new ShareAction(getView())
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA)
                .withMedia(umWeb)
                .setCallback(new ShareCallback())
                .open();
    }

    public void enter(String pwd, String code) {
        MatchModel.getInstance().enter(mMatchID, pwd, code).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                getView().setEnrolled();
                loadData();
            }
        });
    }

    public void sign() {
        MatchModel.getInstance().sign(mMatchID).unsafeSubscribe(new ServicesResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                getView().setSigned();
            }
        });
    }

    public void getBattleID() {
        MatchModel.getInstance().getBattleID(mMatchID).unsafeSubscribe(new ServicesResponse<Battle>() {
            @Override
            public void onNext(Battle battle) {
                if (battle.getBattle_id() > 0) {
                    Intent intent = new Intent(getView(), BattleActivity.class);
                    intent.putExtra("battle_id", battle.getBattle_id());
                    getView().startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        UMShareAPI.get(getView()).onActivityResult(requestCode, resultCode, data);
    }

    public List<Fragment> getFragments() {
        Bundle bundle = new Bundle();
        bundle.putInt("match_id", mMatchID);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MatchInfoFragment());

        MatchRulesFragment rulesFragment = new MatchRulesFragment();
        rulesFragment.setArguments(bundle);
        fragments.add(rulesFragment);

        ScheduleListFragment scheduleFragment = new ScheduleListFragment();
        scheduleFragment.setArguments(bundle);
        fragments.add(scheduleFragment);
//
//        ChatRoomFragment chatroomFragment = ChatRoomFragmentPresenter.newInstance(mMatchID, "c");
//        chatroomFragment.setInputView(getView().getContent());
//        fragments.add(chatroomFragment);
        return fragments;
    }

    public String getFormatDate(long time) {
        String days = (time / (1000 * 3600 * 24)) + "";
        String hours = ((time % (1000 * 3600 * 24)) / (1000 * 3600)) + "";
        String minutes = ((time % (1000 * 3600)) / (1000 * 60)) + "";
        String seconds = (time % (1000 * 60) / 1000) + "";
        return String.format("%1$s:%2$s:%3$s:%4$s", days, hours, minutes, seconds);
    }

}
