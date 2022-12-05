<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp" %>  	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<ul>
<c:choose>
	<c:when test="${sessionScope.user==null}">
		<li><a href='/test/registrationcontroller'><font color="white" size="4">Реєстрація</font></a></li>
	</c:when>
	
	<c:when test="${sessionScope.user!=null}">
		<li><a href='/test/logincontroller?key'><font color="white" size="4">Вихід</font></a></li>
	</c:when>
</c:choose>

<li><a href='/test/secretservlet'><font color="white" size="4">Секретний Servlet</font></a></li>
</ul>

<center>
	<c:choose>
	<c:when test="${sessionScope.user==null}">
				<form action='/test/logincontroller' method='post'>
			<table border='0'>
				<tr>
					<td width='100'><font color="white" size="4">Логін</font></td>
					<td><input type='text' name='login' /></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Пароль</font></td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td align='center' colspan='2'><input type='submit'
						value='Send' /></td>
				</tr>
			</table>
				</form>
				<br><br>
		<font color="white" size="5">${message}</font><br>
	</c:when>
	
	<c:when test="${sessionScope.user!=null}">
				<center>
					<font color="white" size="4">Шановний користувач ${sessionScope.user.name}, ви успішно увійшли до особистого кабінету </form>
				</center>
	</c:when>
</c:choose>
</center>
</body>
</html>
<%@ include file="footer.jsp" %> 