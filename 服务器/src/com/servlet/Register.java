package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sqlserver.DBOper;



import java.sql.*;

public class Register extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
		doPost(request, response);
}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		DBOper db = new DBOper();
		try{
			db.getConn();
			String sql = "insert into useinfo(Account,Password) values (?,?)";
			int rs = db.executeUpdate(sql,new String[] { account,password });
			if(rs > 0){
//				out.println("×¢²á³É¹¦");
				response.getOutputStream().write("×¢²á³É¹¦".getBytes("UTF-8"));
			}else{
//				out.println("×¢²áÊ§°Ü");
				response.getOutputStream().write("×¢²áÊ§°Ü".getBytes("UTF-8"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
