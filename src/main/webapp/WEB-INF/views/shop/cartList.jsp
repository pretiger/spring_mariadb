<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<script>
$(function(){
	$("#btnUpdate").click(function(){
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("장바구니를 비우시겠습니까?")){
			location.href="${path}/shop/deleteAllCart.do?userid=${sessionScope.userid}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>장바구니</h2>
<form name="form1" method="post" action="${path}/shop/updateCart.do">
<c:choose>
<c:when test="${map.sum > 0}">
<table border="1" width="500px">
	<tr>
		<th>상품명</th>
		<th>단가</th>
		<th>수량</th>
		<th>금액</th>
		<th>&nbsp;</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.product_name}</td>
		<td><fmt:formatNumber value="${row.price}" pattern="#,###,###" /> </td>
		<td><input type="number" min="1" max="100" width="30px" name="amount" 
			value="<fmt:formatNumber value="${row.amount}" pattern="#,###,###" />"></td>
		<td><fmt:formatNumber value="${row.money}" pattern="#,###,###" /></td>
		<td><a href="${path}/shop/deleteCart.do/${row.cart_id}">[삭제]</a>
			<input type="hidden" name="cart_id" value="${row.cart_id}"></td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="5" align="right">
			장바구니 금액합계 : <fmt:formatNumber value="${map.sum}" pattern="#,###,###" /><br>
			배송료 : <fmt:formatNumber value="${map.fee}" pattern="#,###,###" /><br>
			총금액 : <fmt:formatNumber value="${map.totSum}" pattern="#,###,###" />
		</td>
	</tr>
	<tr>
		<td colspan="5" align="center">
			<input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnDelete" value="장바구니 비우기">
		</td>
	</tr>
</table>
</c:when>
<c:otherwise>
	장바구니가 비었습니다.
</c:otherwise>
</c:choose>
</form>
<input type="button" value="목록" onclick="location.href='${path}/shop/list.do'">
</body>
</html>