<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<center>
	<font color="white" size="5">${sessionScope.user.name}, ваше замовлення успішно оформлено. Дякуємо за покупку!</font> 
</center>
</body>
</html>
<%@ include file="footer.jsp"%>
