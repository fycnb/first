package db;

import java.sql.*;

public class DBOper {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConn() 
			throws ClassNotFoundException,SQLException,InstantiationException,IllegalAccessException{
			String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String URL = "jdbc:sqlserver://114.115.137.58:1433;DatabaseName=Prussia";
			String usename = "sa";
			String password = "123456";
			Class.forName(DRIVER).newInstance();
			conn = DriverManager.getConnection(URL,usename,password);
			return conn;
	}
	
	public void closeALL(){
		if (rs != null){
			try{
				rs.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if (pstmt != null){
			try{
				pstmt.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		if (conn != null){
			try{
				conn.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public int executeUpdate(String sql, String[] param)  {
		// TODO Auto-generated method stub
		int num = 0;
		try{
			pstmt = conn.prepareStatement(sql);
			if(param != null){
				for(int i=0;i<param.length;i++){
					pstmt.setString(i+1,param[i]);
				}
			}
			num = pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return num;
	}
     

	public ResultSet executeQuery(String sql, String[] param) {
		try{
			pstmt = conn.prepareStatement(sql);
			if(param != null){
				for(int i=0;i<param.length;i++){
					pstmt.setString(i+1,param[i]);
				}
			}
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}      
	
	public String hint(String sql, String[] param) {
		String aString = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			aString = e.getMessage();
		}
		return aString;
	}
}
