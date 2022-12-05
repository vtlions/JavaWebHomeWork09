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

		<div id="menu" align="center>
			<ul>
				<li class="${pageContext.request.contextPath}/index">Головна</a></li>
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
	
</body>
</html>