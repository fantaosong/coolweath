package com.coolweather.app.util;

/**
 * Created by fantaosong on 2016-09-03.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
