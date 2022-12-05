<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header2.jsp"%>

<!DOCTYPE html>
<!--
Design by Free CSS Templates
http://www.freecsstemplates.org
Released for free under a Creative Commons Attribution 2.5 License

Name       : Photoshoot 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20110926

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>Магазин для колекціонерів</title>
<link href="${pageContext.request.contextPath}/static/styles/style.css"
	rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/text/javascript/jquery-1.6.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/text/javascript/jquery.poptrox-0.1.js"></script>
</head>
<body>
	
	<br>
	<br>
	<div id="header" class="container">
		<div id="logo" align="left">
			<h1>
				<a href="#">Магазин для колекціонерів</a>
			</h1>
			<p>
				Монети, бони, марки<a href="http://www.freecsstemplates.org"></a>
			</p>
		</div>
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
	</div>
	<!-- end #header -->
	<div id="poptrox">
		<!-- start -->
		<ul id="gallery">
			<li><a
				href="${pageContext.request.contextPath}/static/images/products/${pieceofproducts.id}.jpg"><img
					src="${pageContext.request.contextPath}/static/images/products/${pieceofproducts.id}.jpg"
					height="300" title="${pieceofproducts.product_description}" alt="" /></a></li>
			<li></li>
			<li></li>
		</ul>
		<br class="clear" />
		<script type="text/javascript">
			$('#gallery').poptrox();
		</script>
		<!-- end -->
	</div>

	<center>
		<table border='1'>
			<tr>
				<td width='300'><font color="white" size="4">${pieceofproducts.product_name}</font></td>
				<td width='300'></td>
			</tr>
			<tr>
				<td width='300'><font color="white" size="4">${pieceofproducts.product_description}</font></td>
			</tr>
			<tr>
				<td width='300'><font color="white" size="4">${pieceofproducts.product_price}</font></td>
				<td>
					<form action='${pageContext.request.contextPath}/cartcontroller'
						method='post'>
						<input type='hidden' name='id' value='${pieceofproducts.id}' /> 
						<input type='hidden' name='piece' value='1' />
						<input type='text' name='quantity' value='1' /> 
						<input type='submit' value='Додати до корзини' />
					</form>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>
<%@ include file="footer.jsp"%>