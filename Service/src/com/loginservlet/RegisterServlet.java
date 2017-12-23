package com.loginservlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dbconn.*;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.exceptionMappingType;

//import net.sf.json.JSONObject;
public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
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

		response.setContentType("text/html charset=utf-8");
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
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println(username + password);

		
        StringBuffer sb = new StringBuffer();
        System.out.println(sb);
        String s = null;
        try{
        BufferedReader br = request.getReader();
        System.out.println(br);
            while((s=br.readLine())!=null){
                sb.append(s);
            }
		}catch (Exception e){
			
		}
//        String streamIn = ReadStream.read(new BufferedInputStream(request.getInputStream())); 
//		String str = request.getParameter("userjson");
//		System.out.println(str);
//		JSONObject obj =JSONObject.parseObject(str);
		JSONObject obj =JSONObject.parseObject(sb.toString());
		System.out.println(obj);
		String jsonusername = obj.getString("userName");
		String jsonpassword = obj.getString("userPwd");
        
	    UserRegister tb = new UserRegister();
        UserBean ub =new UserBean();
        ub.setUserName(jsonusername);
        ub.setPassword(jsonpassword);
        tb.setUserBean(ub);
        try {
			tb.regist();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean b = tb.RegisterCheck(jsonusername,jsonpassword);
		System.out.println(b);
		response.setContentType("text/html charset=utf-8");
		if(b){
			response.getOutputStream().write("×¢²á³É¹¦".getBytes("utf-8"));
		}else{
        	response.getOutputStream().write("×¢²áÊ§°Ü".getBytes("utf-8"));
		}
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

//		response.setContentType("text/html");
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
