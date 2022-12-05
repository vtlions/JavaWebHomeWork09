<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<li><a href='/test/logincontroller'><font color="white" size="4">Логін</font></a></li>
	<li><a href='/test/secretservlet'><font color="white" size="4">Секретний Servlet</font></a></li>
</ul>

<c:choose>
	<c:when test="${sessionScope.user==null}">
	<center>
		<form action='' method='post'>
			<table border='0'>
				<tr>
					<td width='100'><font color="white" size="4"><font color="white" size="4">Логін</font></font></td>
					<td><input type='email' name='login' value='${login}' /></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Пароль</font></td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Повтор пароля</font></td>
					<td><input type='password' name='rePassword' /></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Ім'я</font></td>
					<td><input type='text' name='fullName' value='${fullName}' /></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Регіон</font></td>
					<td><select name='region'>
							<option value='Lviv' ${lviv}>Lviv region</option>
							<option value='Kyiv' ${kyiv}>Kyiv region</option>
							<option value='Kharkiv' ${kharkiv}>Kharkiv region</option>
					</select></td>
				</tr>
				<tr>
					<td><font color="white" size="4">Стать</font></td>
					<td>
						Ж <input type='radio' value='F' name='gender' ${f} /> 
					    Ч <input type='radio' value='M' name='gender' ${m} />
					</td>
				</tr>
				<tr>
					<td><font color="white" size="4">Коментар</font></td>
					<td><textarea cols='10' rows='5' name='comment'>${comment}</textarea>
					</td> <font color="white" size="4">${errors}</font>
				</tr>
				<tr>
					<td><font color="white" size="4">Я погоджуюсь з умовами сайту</font></td>
					<td><input type='checkbox' name='agreement' /></td>
				</tr>
				<tr>
					<td align='center' colspan='2'><input type='submit'
						value='Send' /></td>
				</tr>
			</table>
		</form>
	</center>
	</c:when>
	
	<c:when test="${sessionScope.user!=null}">
		<br><font color="white" size="6">Ви вже успішно авторизовані
	</font>
	</c:when>
</c:choose>
</body>
</html>
<%@ include file="footer.jsp" %> 