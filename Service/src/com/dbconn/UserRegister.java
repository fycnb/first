package com.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserRegister {

	private UserBean userBean;
	private boolean islogin;
	private boolean isRegister;
	static Connection dbConn;
	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动 
	public static String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //连接服务器和数据库sample 
	public static String userName = "xue"; //用户名 
	public static String userPwd = "miyazono0704"; //密码   
	//数据库连接
	public void UserRegister(){
		
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //加载JDBC驱动 
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //连接服务器和数据库sample 
//		String userName = "xue"; //用户名 
//		String userPwd = "miyazono0704"; //密码   

  
            try {
				Class.forName(driverName);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
  
	    }
		
	
	
    public static Connection getConnection(){  
        try{  
        	dbConn=DriverManager.getConnection(dbURL,userName,userPwd);  
        }  
        catch(SQLException e){  
            e.printStackTrace();  
        }  
        return dbConn;  
    }  
    
	
	//设置待注册的用户
	public void setUserBean(UserBean userBean){
		this.userBean = userBean;
	}
	
	//注册用户
	public void regist() throws Exception{
		String reg = "insert into userinfo(userid,userpwd) values(?,?)";
	     Connection conn=UserRegister.getConnection();  
//	     if(conn==null){  
//	         System.out.println("数据库连接失败！");  
//	     }  else{
//	     	System.out.println("数据库连接成功！！"); 
//	     }
		try{
			PreparedStatement pstmt = conn.prepareStatement(reg);
			pstmt.setString(1, userBean.getUserName());
			pstmt.setString(2, userBean.getPassword());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	
	
	//登录检测   /////会话
	public boolean LoginCheck(String username,String password){
		try{
		     Connection conn=UserRegister.getConnection();  
            Statement stmt=dbConn.createStatement();  
        String sql="select * from userinfo where userid='"+username+"'and userpwd='"+password+"'";  
        ResultSet rs= stmt.executeQuery(sql); 
        if(rs.next()){
//        	UserBean userbean = new UserBean();
//        	userbean.setUserName(username);
//        	userbean.setPassword(password);
//        }
        	String passwordR = new String(rs.getString("userpwd"));
        	if(passwordR.equals(password)){
        		System.out.println("passwordR");
        		return islogin=true;
        	}
        	else{
        		return islogin=true;
        	}
        }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return islogin;
	}
	
//	userpwd='"+password+"'
	public boolean RegisterCheck(String username,String password){
		try{
		     Connection conn=UserRegister.getConnection();  
            Statement stmt=dbConn.createStatement();  
        String sql="select * from userinfo where userid='"+username+"'and userpwd='"+password+"'";  
        ResultSet rs= stmt.executeQuery(sql); 
        if(rs.next()){
        	String userID = new String(rs.getString("userid"));
        	System.out.println(userID);
        	if(userID.equals(username)){
        		System.out.println(userID);
//        	UserBean ub = new UserBean();
//        	ub.setUserName(username);
//        	ub.setPassword(password);
        	
        		return isRegister=true;
        	}
        	else{
        		return isRegister=true;
        	}
        }
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isRegister;
	}

}
