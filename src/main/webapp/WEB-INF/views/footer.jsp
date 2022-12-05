<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			  
<div id="sidebar">
	<table border=1>
         <tr>
             <td width="252" align="left">   
               <c:choose>
					<c:when test="${sessionScope.user==null}">
						<font color="white" size="5"> Ви не авторизовані</font><br />
					</c:when>
	
			  		<c:when test="${sessionScope.user!=null}">
					<font color="white" size="5"> Ви увійшли як ${sessionScope.user.name}</font><br />
					</c:when>
				</c:choose>         
                  
			<c:if test="${sessionScope.cart!=null}">
				<font color="white" size="4">В корзині збережено позицій: ${sessionScope.cart.size()}.</font>
			</c:if>
         </td>
       </tr>
   </table>
                   
					<ul>
					<li><a></a></li>
						<li><a href="${pageContext.request.contextPath}/productcontroller?category=1"><font color="white" size="4">Монети</font></a></li>
						<li><a href="${pageContext.request.contextPath}/productcontroller?category=2"><font color="white" size="4">Бони</font></a></li>
						<li><a href="${pageContext.request.contextPath}/productcontroller?category=3"><font color="white" size="4">Марки</font></a></li>
						<li><a></a></li>
						<li><a></a></li>
						<li><a href="${pageContext.request.contextPath}/cartcontroller?key"><font color="white" size="4">Корзина
						<c:if test="${sessionScope.cart!=null}">
							${sessionScope.cart.size()}
						</c:if>	
						</font></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	
</div>
<!-- end #footer -->
</body>
</html>
