<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
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

<c:forEach items="${products}" var="product">
	<table border='1'>
		<tr>
			<td width='300'><font color="white" size="4">${product.product_name}</font></td>
			<td width='300'></td>
		</tr>
		<tr>
			<td width='300'>
			<a href="${pageContext.request.contextPath}/productcontroller?id=${product.id}">
					<img border="0" alt="W3Schools"
					src='${pageContext.request.contextPath}/static/images/products/${product.id}_small.jpg'
					width='auto' height='100px' /></a>
					</td>
			<td width='300'><font color="white" size="4">${product.product_description}</font></td>
		</tr>
		<tr>
			<td width='300'><font color="white" size="4">${product.product_price}</font></td>
			<td>
				<form action='${pageContext.request.contextPath}/cartcontroller'
					method='post'>
					<input type='hidden' name='id' value='${product.id}' /> <input
						type='text' name='quantity' value='1' size='3' />
						<input type='submit'
						value='Додати до корзини' />
				</form>
			</td>
		</tr>
	</table>
	<br><br>

</c:forEach>
<%@ include file="footer.jsp"%>
