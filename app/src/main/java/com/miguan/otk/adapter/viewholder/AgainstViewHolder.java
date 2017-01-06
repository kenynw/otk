package com.miguan.otk.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.miguan.otk.R;
import com.miguan.otk.model.bean.VS;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class AgainstViewHolder extends BaseViewHolder<VS> {

    @Bind(R.id.tv_vs_player_a)
    TextView mTvA;

    public AgainstViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_against);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(VS data) {
        mTvA.setText(String.format("%s VS %s", data.getPlayer_a(), data.getPlayer_b()));
    }
}