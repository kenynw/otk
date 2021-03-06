package com.miguan.otk.model.services;

import com.google.gson.Gson;
import com.miguan.otk.utils.LUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class WrapperResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private static final String TAG = "WrapperResponseBodyConverter";

    private final Type mType;

    WrapperResponseBodyConverter(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            JSONObject data = new JSONObject(value.string());

            LUtils.log(TAG, data.toString());

            int status = data.getInt("status");
            if (status != 1) {
                throw new ServiceException(status, data.getString("msg"));
            }

            String result = "";
            if (data.has("data")) {
                if (!data.isNull("data")) result = data.opt("data").toString();
                else return null;
            } else {
                return new Gson().fromJson(data.toString(), mType);
            }

            return new Gson().fromJson(result, mType);
        } catch (JSONException e) {
            throw new ServiceException(0, "数据解析错误");
        }
    }

}
