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

public class InquireGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		String s = null;

		BufferedReader br = request.getReader();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		JSONObject obj = JSONObject.parseObject(sb.toString());

		String kind = obj.getString("kind");
		String money = obj.getString("money");
		String id = obj.getString("mymoney");

		String m1 = "9999";
		String m2 = "0";
		if (money.equals("1~10")) {
			m1 = "11";
		} else if (money.equals("11~30")) {
			m1 = "31";
			m2 = "10";
		} else if (money.equals("30+")) {
			m2 = "30";
		}

		DBOper db = new DBOper();
		JSONArray js = new JSONArray();
		try {
			db.getConn();
			ResultSet rs;

			if (id.equals("全部")) {
				if (kind.equals("全部")) {
					String sql = "select * from goodsinfo where goodsmoney < ? and goodsmoney > ? and goodsnumber!=0   ";
					rs = db.executeQuery(sql, new String[] { m1, m2 });
				} else {
					String sql = "select * from goodsinfo where goodsclassid = (select goodsclassid from goodsclass where classname =?) and goodsmoney < ? and goodsmoney > ? and goodsnumber!=0 ";
					rs = db.executeQuery(sql, new String[] { kind, m1, m2 });
				}
			} else if (kind.equals("全部")) {
				String sql = "select * from goodsinfo where goodsmoney < ? and goodsmoney > ? and goodsmoney<(select usermoney from userinfo where userid =?) and goodsnumber!=0   group by goodsid,goodsname,goodsclassid,goodsnumber,goodsmoney,goodsimage,goodsplace having goodsid not in(select classid from cashdetails where userid = ? and [state] = (CONVERT(varchar(100), GETDATE(), 5)) and cashclass = 'goods' group by classid having sum(cashnumber)>9)";
				rs = db.executeQuery(sql, new String[] { m1, m2, id, id });
			} else {
				String sql = "select * from goodsinfo where goodsclassid = (select goodsclassid from goodsclass where classname =?) and goodsmoney < ? and goodsmoney > ? and goodsnumber!=0 and goodsmoney<(select usermoney from userinfo where userid =?) group by goodsid,goodsname,goodsclassid,goodsnumber,goodsmoney,goodsimage,goodsplace having goodsid not in(select classid from cashdetails where userid = ? and [state] = (CONVERT(varchar(100), GETDATE(), 5)) and cashclass = 'goods' group by classid having sum(cashnumber)>9)";
				rs = db.executeQuery(sql, new String[] { kind, m1, m2, id, id });
			}

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
