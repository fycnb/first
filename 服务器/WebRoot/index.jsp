<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="from" action="Login" method="post">
<table>
<tr><td>用户名</td><td><input type="text" name="account"></td></tr>
<tr><td>密码</td><td><input type="text" name="password"></td></tr>
<tr><td colspan="2" align="center"><input type="submit"  value="登陆"></td></tr>
</table>
</form>
</body>
</html>
