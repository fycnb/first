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

public class RefreshMoneyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuffer sb = new StringBuffer();
		String s = null;

		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		JSONObject obj = JSONObject.parseObject(sb.toString());

		String what = obj.getString("what");
		String id = obj.getString("id");
		DBOper db = new DBOper();
		try {
			db.getConn();
			String sql;
			String money = null;
			if (what.equals("goods")) {
				sql = "select goodsmoney from goodsinfo where goodsid= ?";
			} else {
				sql = "select servicemoney from serviceinfo where serviceid= ?";
			}
			ResultSet rs = db.executeQuery(sql, new String[] { id });
			while (rs.next()) {
				if (what.equals("goods")) {
					money = new String(rs.getString("goodsmoney"));
				} else {
					money = new String(rs.getString("servicemoney"));
				}
			}
			response.getOutputStream().write(money.getBytes("utf-8"));
		} catch (Exception e) {

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
