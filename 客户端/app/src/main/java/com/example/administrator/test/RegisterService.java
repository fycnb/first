package com.example.administrator.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/11/30.
 */

public class RegisterService {
    public static String registerByGet(String account,String password){
        try {
            String path = "http://192.168.1.2:8080/test1/Register?account="
                    + URLEncoder.encode(account, "UTF-8")
                    + "&password=" + URLEncoder.encode(password);
            URL url = new URL(path);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int code = conn.getResponseCode();
            if(code == 200){
                InputStream is = conn.getInputStream();
                String text = StreamTools.readInputStream(is);
                return text;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
