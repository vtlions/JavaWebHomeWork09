<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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

</head>
<body>
	<div id="header" class="container">
		<div id="logo">
			<h1>
				<a href="#">Магазин для колекціонерів</a>
			</h1>
			<p>
				Монети, бони, марки<a href="http://www.freecsstemplates.org"></a>
			</p>
		</div>
		<div id="menu">
			<ul>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}/index">Головна</a></li>
				<li><a
					href="${pageContext.request.contextPath}/productcontroller">Товари</a></li>
				<li><a
					href="${pageContext.request.contextPath}/registrationcontroller">Реєстрація</a></li>
				<li><a
					href="${pageContext.request.contextPath}/logincontroller">Вхід</a></li>
				<li><a href="${pageContext.request.contextPath}/cartcontroller?key">Корзина  
				
<c:if test="${sessionScope.cart!=null}">
${sessionScope.cart.size()}
</c:if>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- end #header -->
	<div id="poptrox">
		<!-- start -->
		<ul id="gallery">
			<li><a href="${pageContext.request.contextPath}/productcontroller?id=1">
			<img src="${pageContext.request.contextPath}/static/images/products/1_small.jpg"
					title="Україна, аркуш Українська мрія" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/productcontroller?id=2"><img
					src="${pageContext.request.contextPath}/static/images/products/2_small.jpg"
					title="Канада (Ньюфаундленд), 50 центів, 1919" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/productcontroller?id=3"><img
					src="${pageContext.request.contextPath}/static/images/products/3_small.jpg"
					title="Німеччина, 3 марки, 1911" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/productcontroller?id=4"><img
					src="${pageContext.request.contextPath}/static/images/products/4_small.jpg"
					title="Аркуш Русскій воєнний корабль … ВСЬО! Слава нації!" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/productcontroller?id=7"><img
					src="${pageContext.request.contextPath}/static/images/products/7_small.jpg"
					title="Аркуш Доброго вечора, ми з України!" alt="" /></a></li>
			<li><a
				href="${pageContext.request.contextPath}/productcontroller?id=6"><img
					src="${pageContext.request.contextPath}/static/images/products/6_small.jpg"
					title="Канада, 1 долар, 1939, Королівський візит" alt="" /></a></li>
			<li></li>
			<li></li>
		</ul>
		<br class="clear" />
		<script type="text/javascript">
			$('#gallery').poptrox();
		</script>
		<!-- end -->
	</div>
	<div id="page">
		<div id="bg1">
			<div id="bg2">
				<div id="bg3">
					<div id="content"></div>
</body>
</html>