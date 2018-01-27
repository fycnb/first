package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import db.DBOper;

public class LoginServlet extends HttpServlet {


	public LoginServlet() {
		super();
	}


	public void destroy() {
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
        String s = null;
        try{
        BufferedReader br = request.getReader();
            while((s=br.readLine())!=null){
                sb.append(s);
            }
		}catch (Exception e){
			e.printStackTrace();
		}
        
        ////
		JSONObject obj =JSONObject.parseObject(sb.toString());
		System.out.println(obj);

		String password = obj.getString("password");
		String account = obj.getString("account");
        System.out.println(password+account);
		response.setContentType("text/html");

		DBOper db = new DBOper();
		try
			{
			db.getConn();
			String sql="select Userid from Users where account= ? and mypassword= ?";
			ResultSet rs= db.executeQuery(sql, new String[]{ account,password });
			if(rs.next()){
				
				String volnum = new String(rs.getString("volnum"));
				System.out.print(volnum);
				
				response.getOutputStream().write(volnum.getBytes("utf-8"));

			}else {
				response.getOutputStream().write("µÇÂ¼Ê§°Ü".getBytes("utf-8"));

			}	
		} catch(Exception e) {
    	e.printStackTrace();
		} 
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
