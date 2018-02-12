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

public class UpdateInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		DBOper db = new DBOper();
		JSONArray js = new JSONArray();
		try {
			db.getConn();

			String sql = "select usermoney,userimage from userinfo where userid= ?";
			ResultSet rs = db.executeQuery(sql, new String[] { id });

			while (rs.next()) {

				JSONObject json = new JSONObject();

				String money = new String(rs.getString("usermoney"));
				String image = new String(rs.getString("userimage"));
				json.put("money", money);
				json.put("image", image);

				js.add(json);

			}

			response.getOutputStream().write(js.toString().getBytes("utf-8"));
		} catch (Exception e) {

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
