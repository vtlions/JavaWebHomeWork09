<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<ul>
<li><a href='/test/logincontroller?key'><font color="white" size="4">Вихід</font></a></li>
<li><a href='/test/secretservlet'><font color="white" size="4">Секретний Servlet</font></a></li>
</ul>
<br><br><font color="white" size="6">Ви вже успішно авторизовані</font>
</body>
</html>
<%@ include file="footer.jsp"%>
