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

public class CashGoodsServlet extends HttpServlet {

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
		String goodsid = obj.getString("goodsid");
		String number = obj.getString("number");
		String money = obj.getString("money");
		String str = obj.getString("str");

		DBOper db = new DBOper();
		try {
			db.getConn();
			String sql = "insert into cashdetails values(?,?,?,?,?,?)";
			String hint = db.hint(sql, new String[] { id, "goods", goodsid,
					number, money, str });

			response.getOutputStream().write(hint.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
