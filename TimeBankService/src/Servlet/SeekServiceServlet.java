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

public class SeekServiceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String s = null;

		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		JSONObject obj = JSONObject.parseObject(sb.toString());

		String str = "%" + obj.getString("str") + "%";
		DBOper db = new DBOper();

		JSONArray js = new JSONArray();
		try {
			db.getConn();

			String sql = "select * from serviceinfo where people like ? or serviceclassid = (select serviceclassid from serviceclass where serviceclassname like ?)";
			ResultSet rs = db.executeQuery(sql, new String[] { str, str });

			while (rs.next()) {

				JSONObject json = new JSONObject();

				String serviceid = new String(rs.getString("serviceid"));
				String people = new String(rs.getString("people"));
				String phone = new String(rs.getString("phone"));
				String fame = new String(rs.getString("fame"));
				String servicemoney = new String(rs.getString("servicemoney"));
				String serviceimage = new String(rs.getString("serviceimage"));

				json.put("name", people);
				json.put("money", servicemoney);
				json.put("image", serviceimage);
				json.put("fame", fame);
				json.put("id", serviceid);
				json.put("phone", phone);

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
