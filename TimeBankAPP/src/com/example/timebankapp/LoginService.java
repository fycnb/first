package com.example.timebankapp;





import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginService {
  
    public static String LoginByPost(String account, String password){
        try{
            
            JSONObject userjson = new JSONObject();
            userjson.put("account",account);
            userjson.put("password",password);
            System.out.println(userjson);
            String content = String.valueOf(userjson);


            //访问的资源路径
            String path = "http://192.168.0.107:8080/TimeBank/LoginServlet?";

            //创建url实例
            URL url = new URL(path);

            //获取HttpURLConnection对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置超时对象
            conn.setConnectTimeout(5000);

            //指定请求方式
            conn.setRequestMethod("POST");

            //将数据传给服务器
            conn.setDoOutput(true);
            conn.setDoInput(true);

//            //转换为字节数组
//            byte[] data = (userjson.toString()).getBytes();

            //设置请求头
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            conn.setRequestProperty("accept","application/json");
            conn.setRequestProperty("ser-Agent", "Fiddler");
            //            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//            conn.setRequestProperty("Content-Length",String.valueOf(data.length));

//            //请求连接
//            conn.connect();

            //得到输出流
            OutputStream os = conn.getOutputStream();
            os.write(content.getBytes());
            os.close();

            int code = conn.getResponseCode();
            if(code==200){
                //                Toast.makeText(this, "Post完成", 1).show();
                //得到服务器返回的输入流
                InputStream is = conn.getInputStream();

                //将输入流转换成字符串
                String text = StreamTools.readInputStream(is);
//                System.out.print(text);

                System.out.println(text);

                return text;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
