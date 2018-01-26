package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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

		String a= "123";
		if("zhangsan".equals(account) && a.equals(password)){
			response.getOutputStream().write("µÇÂ¼³É¹¦".getBytes("UTF-8"));
		}else{
			response.getOutputStream().write("µÇÂ¼Ê§°Ü".getBytes("UTF-8"));
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
