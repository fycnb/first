package com.example.timebankapp.Convert;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.example.timebankapp.StreamTools;

import android.util.Log;

public class CashServiceService {

	public static String CashServiceByPost(String id, String serviceid, String time, String money) {
		try {

			JSONObject userjson = new JSONObject();
			userjson.put("id", id);
			userjson.put("serviceid", serviceid);
			userjson.put("time", time);
			userjson.put("money", money);

			String content = String.valueOf(userjson);
			String path = "http://192.168.0.107:8080/login/CashServiceServlet?";

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
}
