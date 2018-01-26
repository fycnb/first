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


            //���ʵ���Դ·��
            String path = "http://192.168.0.107:8080/TimeBank/LoginServlet?";

            //����urlʵ��
            URL url = new URL(path);

            //��ȡHttpURLConnection����
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //���ó�ʱ����
            conn.setConnectTimeout(5000);

            //ָ������ʽ
            conn.setRequestMethod("POST");

            //�����ݴ���������
            conn.setDoOutput(true);
            conn.setDoInput(true);

//            //ת��Ϊ�ֽ�����
//            byte[] data = (userjson.toString()).getBytes();

            //��������ͷ
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            conn.setRequestProperty("accept","application/json");
            conn.setRequestProperty("ser-Agent", "Fiddler");
            //            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//            conn.setRequestProperty("Content-Length",String.valueOf(data.length));

//            //��������
//            conn.connect();

            //�õ������
            OutputStream os = conn.getOutputStream();
            os.write(content.getBytes());
            os.close();

            int code = conn.getResponseCode();
            if(code==200){
                //                Toast.makeText(this, "Post���", 1).show();
                //�õ����������ص�������
                InputStream is = conn.getInputStream();

                //��������ת�����ַ���
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
