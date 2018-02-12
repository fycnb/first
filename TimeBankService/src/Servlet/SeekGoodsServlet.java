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

public class SeekGoodsServlet extends HttpServlet {

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

			String sql = "select * from goodsinfo where goodsname like ? or goodsplace like ? or goodsclassid = (select goodsclassid from goodsclass where classname like ?)";
			ResultSet rs = db.executeQuery(sql, new String[] { str, str, str });

			while (rs.next()) {

				JSONObject json = new JSONObject();

				String goodsname = new String(rs.getString("goodsname"));
				String goodsnumber = new String(rs.getString("goodsnumber"));
				String goodsmoney = new String(rs.getString("goodsmoney"));
				String goodsimage = new String(rs.getString("goodsimage"));
				String goodsplace = new String(rs.getString("goodsplace"));
				String goodsid = new String(rs.getString("goodsid"));

				json.put("name", goodsname);
				json.put("money", goodsmoney);
				json.put("image", goodsimage);
				json.put("number", goodsnumber);
				json.put("site", goodsplace);
				json.put("id", goodsid);

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
