<%@ include file="header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<font color="white" size="7">ЦЕЙ МАГАЗИН СТВОРЕНО ДЛЯ ЗАВЗЯТИХ
	КОЛЕКЦІОНЕРІВ МОНЕТ ТА БАНКНОТ </font>
<br><br><br><br>
<font color="white" size="6">Шановні покупці, до вашої уваги
	пропонуються монети, банкноти та поштові марки різних країн світу.
	Сподіваємося, ви зможете знайти у нашому онлайн-магазині товар, який
	стане окрасою вашої колекції. </font>
	
	<br><br>
<ul>
<c:choose>
	<c:when test="${sessionScope.user==null}">
		<li><a href='/test/logincontroller'><font color="white" size="4">Вхід]</font></a></li>
		<li><a href='/test/registrationcontroller'><font color="white" size="4">Реєстрація</font></a></li>
	</c:when>
	
	<c:when test="${sessionScope.user!=null}">
		<li><a href='/test/logincontroller?key'><font color="white" size="4">Вихід</font></a></li>
	</c:when>
</c:choose>

<li><a href='/test/secretservlet'><font color="white" size="4">Секретний Servlet</font></a></li>
</ul>
<%@ include file="footer.jsp"%>