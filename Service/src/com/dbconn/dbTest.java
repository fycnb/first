package com.dbconn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class dbTest {

		public static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //����JDBC���� 
		public static String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=youqu"; //���ӷ����������ݿ�sample 
		public static String userName = "xue"; //Ĭ���û��� 
		public static String userPwd = "miyazono0704"; //���� 
		public static Connection dbConn;
	    //װ������  
	    static{  
	        try{  
	            Class.forName(driverName);  
	        }  
	        catch(ClassNotFoundException e){  
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
	    
	    //�������ݿ�����  
	    public static void main(String[] args){
	    	
	    	
//	    UserRegister ur = new UserRegister();
//	    UserBean ub = new UserBean();
//	    ub.setUserId("te");
//	    ub.setPassword("te1");
//	    ur.setUserBean(ub);
//	    try {
//	       ur.regist();
//	   } catch (Exception e) {
//	     // TODO Auto-generated catch block
//	       e.printStackTrace();
//	   }
	     Connection conn=dbTest.getConnection();  
	     if(conn==null){  
	         System.out.println("���ݿ�����ʧ�ܣ�");  
	     }  else{
	     	System.out.println("���ݿ����ӳɹ�����"); 
	     }
//	    System.out.println(ur.LoginCheck("te", "te1"));
//	      System.out.println(ur.LoginCheck("u1", "pwd1"));

	        }  
	    //�ر����ݿ�����  
//	    public static void Close(){  
//	        if(dbConn!=null){  
//	            try{  
//	                dbConn.close();  
//	            }  
//	            catch(SQLException e){  
//	                e.printStackTrace();  
//	            }  
//	        }  
//	    }

//      UserRegister ur = new UserRegister();
//      UserBean ub = new UserBean();
//      ub.setUserId("te");
//      ub.setPassword("te1");
//      ur.setUserBean(ub);
//     try {
//       ur.regist();
//   } catch (Exception e) {
//     // TODO Auto-generated catch block
//       e.printStackTrace();
//   }
//     Connection conn=dbTest.getConnection();  
//     if(conn==null){  
//         System.out.println("���ݿ�����ʧ�ܣ�");  
//     }  else{
//     	System.out.println("���ݿ����ӳɹ�����"); 
//     }
//    System.out.println(ur.LoginCheck("t3", "te1"));
//      System.out.println(ur.LoginCheck("u1", "pwd1"));
}
