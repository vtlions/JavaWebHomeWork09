<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.my_button {
	width: 180px;
	height: 90px;
}
</style>
</head>
<body>
	
<ul>
<c:choose>
	<c:when test="${sessionScope.user==null}">
		<li><a href='/test/logincontroller'><font color="white" size="4">Логін</font></a></li>
		<li><a href='/test/registrationcontroller'><font color="white" size="4">Реєстрація</font></a></li>
	</c:when>
	
	<c:when test="${sessionScope.user!=null}">
		<li><a href='/test/logincontroller?key'><font color="white" size="4">Вихід</font></a></li>
	</c:when>
</c:choose>

<li><a href='/test/secretservlet'><font color="white" size="4">Секретний Servlet</font></a></li>
</ul>

<center>
	<h2>
		<c:choose>
	<c:when test="${sessionScope.cart!=null}">
		<h2>Ваша корзина містить наступні товари:</h2>
	</c:when>
	
	<c:when test="${salary==null}">
		<h2>Ваша корзина поки ще порожня.</h2>
	</c:when>
</c:choose>

<c:forEach items="${sessionScope.cart}" var="cart">

	<table border='1'>
				<tr>
					<td width='400'><h3>
							<b><font color="white" size="5">${cart.key.product_name}</font></b>
						</h3></td>
					<td width='400'></td>
				</tr>

				<tr>
					<td width='400'><img
						src='${pageContext.request.contextPath}/static/images/products/${cart.key.id}_small.jpg'
						width='auto' height='100px' /></td>
					<td width='400'><h4>
							<font color="white" size="4">${cart.key.product_description}</font>
						</h4></td>
				</tr>

				<tr>
					<td width='400'><font color="white" size="4">Ціна
							товару: ${cart.key.product_price} грн.</font></td>
					<td><font color="white" size="4">Кількість товару:
							${cart.value}</font></td>
				</tr>
	</table>
	<br><br>
	<c:set var='totalSum' value='${totalSum+cart.key.product_price*cart.value}' />
</c:forEach>

<font color="white" size="5">${keytotalsum} ${totalSum} </font>

<c:if test="${sessionScope.cart!=null}">
	<table>
				<td>
					<form
						action='${pageContext.request.contextPath}/cartcontroller?buy'
						method='post'>
						<br> <br> <input type='hidden' name='totalsum'
							value='${totalSum}' /> <input type='submit' value='Купити'
							class="my_button" />
					</form>
				</td>
	</table>
</c:if>
</center>
</body>
</html>
<%@ include file="footer.jsp"%>
