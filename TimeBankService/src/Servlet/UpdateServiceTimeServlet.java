package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import db.DBOper;

public class UpdateServiceTimeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer sb = new StringBuffer();
		String s = null;

		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		Date date = new Date();
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		int now = time(dateFm.format(date));
		int a = 1;

		JSONObject obj = JSONObject.parseObject(sb.toString());
		String id = obj.getString("id");

		JSONArray js = new JSONArray();
		DBOper db = new DBOper();

		try {
			db.getConn();

			String sql = "select * from servicetime where serviceid =?";
			ResultSet rs = db.executeQuery(sql, new String[] { id });

			while (rs.next()) {

				for (int i = 0; i < 6; i++) {

					String str = "time" + a;
					int time = new Integer(rs.getInt(str));

					if (now < time) {
						JSONObject json = new JSONObject();
						String time2 = week(time);
						json.put("time", time2);
						js.add(json);
					}

					a++;
				}

			}

			response.getOutputStream().write(js.toString().getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	int time(String time) {
		int str = 0;
		Calendar cal = Calendar.getInstance();
		int h = cal.get(Calendar.HOUR_OF_DAY);
		if (h < 20) {
			switch (time) {
			case "星期一":
				str = 1;
				break;
			case "星期二":
				str = 2;
				break;
			case "星期三":
				str = 3;
				break;
			case "星期四":
				str = 4;
				break;
			case "星期五":
				str = 5;
				break;
			case "星期六":
				str = 6;
				break;
			case "星期日":
				str = 7;
				break;
			}
		} else {
			switch (time) {
			case "星期一":
				str = 2;
				break;
			case "星期二":
				str = 3;
				break;
			case "星期三":
				str = 4;
				break;
			case "星期四":
				str = 5;
				break;
			case "星期五":
				str = 6;
				break;
			case "星期六":
				str = 7;
				break;
			case "星期日":
				str = 11;
				break;
			}
		}

		return str;
	}

	String week(int a) {
		String string = null;
		switch (a) {
		case 2:
			string = "本周二";
			break;
		case 3:
			string = "本周三";
			break;
		case 4:
			string = "本周四";
			break;
		case 5:
			string = "本周五";
			break;
		case 6:
			string = "本周六";
			break;
		case 7:
			string = "本周日";
			break;
		case 11:
			string = "下周一";
			break;
		case 12:
			string = "下周二";
			break;
		case 13:
			string = "下周三";
			break;
		case 14:
			string = "下周四";
			break;
		case 15:
			string = "下周五";
			break;
		case 16:
			string = "下周六";
			break;
		case 17:
			string = "下周日";
			break;
		}
		return string;

	}

}
