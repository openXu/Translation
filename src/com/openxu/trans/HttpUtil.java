package com.openxu.trans;

import com.intellij.openapi.ui.Messages;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {

    public static void doGetAsyn(String urlStr, CallBack callBack){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("翻译请求：urlStr="+urlStr);
                    URL url = new URL(urlStr);
                    URLConnection rulConnection = url.openConnection();
                    HttpURLConnection connection = (HttpURLConnection) rulConnection;
                    connection.setRequestMethod("GET");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK){
                        InputStream inputStream = connection.getInputStream();
                        byte[] bytes = new byte[0];
                        bytes = new byte[inputStream.available()];
                        inputStream.read(bytes);
                        String result = new String(bytes, "UTF-8");
                        System.out.println("翻译：responseCode="+responseCode+"  "+result);
                        callBack.onRequestComplete(true, "翻译成功", result);
                        return;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    callBack.onRequestComplete(false, e.getMessage(), "");
                }
                callBack.onRequestComplete(false, "意外错误", "");
            }
        }).start();
    }

    public  interface CallBack{
        public void onRequestComplete(boolean ok, String msg, String result);
    }

}
