package com.example.timebankapp.Convert;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.example.timebankapp.StreamTools;

import android.util.Log;

public class InquireGoodsService {

	public static String InquireGoodsByPost(String kind, String money, String mymoney) {
		try {

			JSONObject userjson = new JSONObject();
			userjson.put("kind", kind);
			userjson.put("money", money);
			userjson.put("mymoney", mymoney);

			String content = String.valueOf(userjson);
			String path = "http://192.168.0.107:8080/login/InquireGoodsServlet?";

			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("ser-Agent", "Fiddler");

			OutputStream os = conn.getOutputStream();
			os.write(content.getBytes());
			os.close();
			int code = conn.getResponseCode();

			if (code == 200) {
				
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);
				
				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String SeekGoodsByPost(String str) {
		try {

			JSONObject userjson = new JSONObject();
			userjson.put("str", str);

			String content = String.valueOf(userjson);
			String path = "http://192.168.0.107:8080/login/SeekGoodsServlet?";

			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("ser-Agent", "Fiddler");
			
			OutputStream os = conn.getOutputStream();
			os.write(content.getBytes());
			os.close();
			int code = conn.getResponseCode();

			if (code == 200) {
				
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);

				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String InquireGoodsClassByPost() {
		try {

			String path = "http://192.168.0.107:8080/login/InquireGoodsClassServlet";

			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("ser-Agent", "Fiddler");

			int code = conn.getResponseCode();

			if (code == 200) {
				
				InputStream is = conn.getInputStream();
				String text = StreamTools.readInputStream(is);

				return text;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
