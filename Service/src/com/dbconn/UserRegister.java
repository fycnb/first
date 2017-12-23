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
	public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC���� 
	public static String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //���ӷ����������ݿ�sample 
	public static String userName = "xue"; //�û��� 
	public static String userPwd = "miyazono0704"; //����   
	//���ݿ�����
	public void UserRegister(){
		
//		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC���� 
//		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //���ӷ����������ݿ�sample 
//		String userName = "xue"; //�û��� 
//		String userPwd = "miyazono0704"; //����   

  
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
    
	
	//���ô�ע����û�
	public void setUserBean(UserBean userBean){
		this.userBean = userBean;
	}
	
	//ע���û�
	public void regist() throws Exception{
		String reg = "insert into userinfo(userid,userpwd) values(?,?)";
	     Connection conn=UserRegister.getConnection();  
//	     if(conn==null){  
//	         System.out.println("���ݿ�����ʧ�ܣ�");  
//	     }  else{
//	     	System.out.println("���ݿ����ӳɹ�����"); 
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
	
	
	//��¼���   /////�Ự
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
