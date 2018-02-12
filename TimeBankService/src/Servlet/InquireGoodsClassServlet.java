package Servlet;

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

public class InquireGoodsClassServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBOper db = new DBOper();

		JSONArray js = new JSONArray();
		try {
			db.getConn();

			String sql = "select * from goodsclass";
			ResultSet rs = db.executeQuery(sql, null);

			while (rs.next()) {

				JSONObject json = new JSONObject();

				String serviceclassname = new String(rs.getString("classname"));

				json.put("name", serviceclassname);

				js.add(json);

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

}
