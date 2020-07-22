<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../include/header.jsp" %>
<title>Insert title here</title>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 상세</h2>
<table>
	<tr align="center">
		<td><img src="${path}/images/${dto.picture_url}" width="300px" height="300px"></td>
			<td align="center">
			<table>
				<tr>
					<td>상품명</td><td>${dto.product_name}</td>
				</tr>
				<tr>
					<td>상품가격</td><td>${dto.price}</td>				
				</tr>
				<tr>
					<td>&nbsp;</td><td>${dto.description}</td>
				</tr>
				<tr>
					<td colspan="2">
						<form method="post" action="${path}/shop/insert.do">
							<input type="hidden" name="product_id" value="${dto.product_id}">
							<select name="amount">
								<c:forEach var="i" begin="1" end="10">
								<option value="${i}">${i}</option></c:forEach>
							</select>&nbsp;개 
							<input type="submit" value="장바구니에 담기">
						</form>
						<input type="button" value="목록" onclick="location.href='${path}/shop/list.do'">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>