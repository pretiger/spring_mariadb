<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 목록</h2>
<table border="1" style="width: 500px;">
	<tr>
		<th>상품코드</th>
		<th>&nbsp;</th>
		<th>상품명</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr align="center">
		<td>${row.product_id}</td>
		<td><img src="${path}/images/${row.picture_url}" width="50px" height="50px"></td>
		<td><a href="${path}/shop/view.do/${row.product_id}">${row.product_name}</a><br>
			<c:if test="${sessionScope.admin_userid != null}">
				<a href="${path}/shop/edit.do/${row.product_id}">[편집]</a>
			</c:if>
		</td>
	</tr>
</c:forEach>
</table>
</body>
</html>