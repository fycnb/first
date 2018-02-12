package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import db.DBOper;

public class CashServiceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String s = null;

		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		JSONObject obj = JSONObject.parseObject(sb.toString());

		String id = obj.getString("id");
		String serviceid = obj.getString("serviceid");
		int time = time(obj.getString("time"));
		String money = obj.getString("money");
		String time1 = time + "";

		DBOper db = new DBOper();
		try {
			db.getConn();
			String sql = "insert into cashdetails values(?,?,?,?,?,?)";
			String hint = db.hint(sql, new String[] { id, "service", serviceid,
					time1, money, "��" });

			response.getOutputStream().write(hint.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	int time(String str) {
		int a = 0;
		switch (str) {
		case "���ܶ�":
			a = 2;
			break;
		case "������":
			a = 3;
			break;
		case "������":
			a = 4;
			break;
		case "������":
			a = 5;
			break;
		case "������":
			a = 6;
			break;
		case "������":
			a = 7;
			break;
		case "����һ":
			a = 11;
			break;
		case "���ܶ�":
			a = 12;
			break;
		case "������":
			a = 13;
			break;
		case "������":
			a = 14;
			break;
		case "������":
			a = 15;
			break;
		case "������":
			a = 16;
			break;
		case "������":
			a = 17;
			break;
		}
		return a;
	}

}
