package com.loginservlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.dbconn.*;

public class LoginServlet extends HttpServlet {

//	private UserBean userBean;
//	private boolean islogin;
//	private boolean isRegister; 
	//���ݿ�����

	        
	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the GET method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		
		
		//�������߻�ȡ�û���Ϣ��Session����
//		HttpSession session = request.getSession();
//		UserBean userbean = (UserBean)session.getAttribute("userbean");
//		if(userbean == null){
//			response.getWriter().print("����û�е�¼");
//		}else{
//			response.getWriter().print("���ѵ�½��");
//		}
//		String name = request.getParameter("username");
//		String pwd = request.getParameter("password");
//		System.out.println(name);
//		System.out.println(pwd);
//		Connection dbConn;
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC���� 
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //���ӷ����������ݿ�sample 
//		String userName = "xue"; //�û��� 
//		String userPwd = "miyazono0704"; //���� 
//		String sql="select * from userinfo where userid='"+name+"'and userpwd='"+pwd+"'";
//        Statement stmt;
//        ResultSet rs;
//        try{  
//            try {
//				Class.forName(driverName).newInstance();
//			} catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//            
//            //�������ݿ�
//        	dbConn=DriverManager.getConnection(dbURL,userName,userPwd); 
//        	
//        	//����Statement����,���ڽ�SQL��䷢�͵����ݿ�
//        	stmt = dbConn.createStatement();
//        	
//			//ִ�����ݿ��ѯ���,���ص���ResultSet����        	
//			rs = stmt.executeQuery(sql);
//			
//			
//			while(rs.next()){
//				String username = rs.getString("userid");
//				String password = rs.getString("userpwd");
//				if(rs != null){
//					rs.close();
//					rs = null;
//				}
//				
//				if(stmt != null){
//					stmt.close();
//					stmt = null;
//				}
//				if(dbConn != null){
//					dbConn.close();
//					dbConn = null;
//				}
//			}
//        }  
//        catch(SQLException e){  
//            e.printStackTrace();  
//        }  
//        String str = request.getParameter("json");
//        
		StringBuffer sb = new StringBuffer();
        String s = null;
        try{
        BufferedReader br = request.getReader();
            while((s=br.readLine())!=null){
                sb.append(s);
            }
		}catch (Exception e){
			
		}
        
		JSONObject obj =JSONObject.parseObject(sb.toString());
		System.out.println(obj);
		String jsonusername = obj.getString("userName");
		String jsonpassword = obj.getString("userPwd");
//        //����json�ַ���str
//        JSONObject obj = JSON.parseObject(str);
//        String jsname = obj.getString("Username");
//        String jspwd = obj.getString("Password");
        
        //���ص�json�ַ��� 
        
        

//			UserBean userbean = new UserBean();
//			userbean.setUserName(name);
//			userbean.setPassword(pwd);
//			request.getSession().setAttribute("userbean", userbean);
//			response.sendRedirect("/Service/IndexService");
//    	}
			UserRegister ur = new UserRegister();
			boolean b = ur.LoginCheck(jsonusername,jsonpassword);
//			System.out.println(b);
			JSONObject fanhui1 = new  JSONObject();
			fanhui1.put("name",jsonusername);
			fanhui1.put("pwd",jsonpassword);
			response.setContentType("text/html charset=utf-8");
//			if(name.equals("abc") && pwd.equals("123"))
//			{
//				response.getOutputStream().write(("��¼�ɹ�").getBytes("utf-8"));
//			}else{
//				response.getOutputStream().write(("��¼ʧ��").getBytes("utf-8"));
//			}
			if(b){
				response.getOutputStream().write("��¼�ɹ�".getBytes("utf-8"));
			}else{
	        	response.getOutputStream().write("��½ʧ��".getBytes("utf-8"));
			}
//			response.getWriter().println();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
		
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		// Put your code here
	}

}
