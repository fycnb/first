package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sqlserver.DBOper;

import java.sql.*;

public class Login extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		DBOper db = new DBOper();
		try
			{
			db.getConn();
			String sql="select * from useinfo where Account= ? and Password= ?";
			ResultSet rs= db.executeQuery(sql, new String[]{ account,password });
			if(rs != null && rs.next()){
//				HttpSession session = request.getSession();
//				session.setAttribute("account", account);
//				Cookie cookie = new Cookie("account", account);
//				response.addCookie(cookie);
//				RequestDispatcher dispatcher =  request.getRequestDispatcher("MainServlet");
//				dispatcher.forward(request, response);
				response.getOutputStream().write("µÇÂ¼³É¹¦".getBytes("utf-8"));
//	    		out.println("µÇÂ¼³É¹¦");
			}else {
				response.getOutputStream().write("µÇÂ¼Ê§°Ü".getBytes("utf-8"));
//				out.println("µÇÂ¼Ê§°Ü");
			}	
		} catch(Exception e) {
    	e.printStackTrace();
		} 
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
