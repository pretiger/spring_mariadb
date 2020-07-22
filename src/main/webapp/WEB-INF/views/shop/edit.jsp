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
		document.form1.action="${path}/shop/updateProduct.do";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			location.href="${path}/shop/deleteProduct.do/${dto.product_id}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 수정/삭제</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table width="600px">
	<tr>
		<td>상품명</td>
		<td><input type="text" name="product_name" value="${dto.product_name}"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="price" value="${dto.price}"></td>
			<%-- "<fmt:formatNumber value="${dto.price}" pattern="#,###,###" />" ></td> --%>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="80" name="description">${dto.description}</textarea></td>
	</tr>
	<tr>
		<td>상품이미지파일</td>
		<td><input type="file" name="file"><img src="${path}/images/${dto.picture_url}"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="hidden" name="product_id" value="${dto.product_id}">
			<input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnDelete" value="삭제">
			<input type="button" value="목록" onclick="location.href='${path}/shop/list.do' ">
		</td>
	</tr>
</table>
</form>
</body>
</html>