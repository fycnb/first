package com.user;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;

//userinfo user1 = new userinfo();

public class userinfo {
	String username;
	String password;
	
	public  userinfo(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getname(){
		return username;
	}
	public void setUSerInfo(String username){
		this.username = username;
	}

	public String getPass(){
		return password;
	}
	public void setPass(String password){
		this.password = password;
	}
}
