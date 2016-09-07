package com.coolweather.app.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fantaosong on 2016-09-03.
 */
public class HttpUtil {
    private static final int BUFFER_SIZE=1024*1024;

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(20000);
                    connection.setReadTimeout(20000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    char[] data = new char[BUFFER_SIZE];
                    int len = reader.read(data);
//                    while ((line = reader.readLine())!= null){
                        response.append(String.valueOf(data,0,len));
//                    }
                    if(listener != null)
                    {
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e)
                {
                    if(listener!=null)
                    {
                        Log.d("HttpUtil-43",address);
                        listener.onError(e);
                    }
                }finally {
                    if(connection!=null)
                    {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
