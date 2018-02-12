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
			case "����һ":
				str = 1;
				break;
			case "���ڶ�":
				str = 2;
				break;
			case "������":
				str = 3;
				break;
			case "������":
				str = 4;
				break;
			case "������":
				str = 5;
				break;
			case "������":
				str = 6;
				break;
			case "������":
				str = 7;
				break;
			}
		} else {
			switch (time) {
			case "����һ":
				str = 2;
				break;
			case "���ڶ�":
				str = 3;
				break;
			case "������":
				str = 4;
				break;
			case "������":
				str = 5;
				break;
			case "������":
				str = 6;
				break;
			case "������":
				str = 7;
				break;
			case "������":
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
			string = "���ܶ�";
			break;
		case 3:
			string = "������";
			break;
		case 4:
			string = "������";
			break;
		case 5:
			string = "������";
			break;
		case 6:
			string = "������";
			break;
		case 7:
			string = "������";
			break;
		case 11:
			string = "����һ";
			break;
		case 12:
			string = "���ܶ�";
			break;
		case 13:
			string = "������";
			break;
		case 14:
			string = "������";
			break;
		case 15:
			string = "������";
			break;
		case 16:
			string = "������";
			break;
		case 17:
			string = "������";
			break;
		}
		return string;

	}

}
