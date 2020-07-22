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
	$("#btnSave").click(function(){
		document.form1.action="${path}/shop/insertProduct.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>상품 등록</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table width="600px">
	<tr>
		<td>상품명</td>
		<td><input type="text" name="product_name"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="price"></td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea rows="5" cols="80" name="description"></textarea></td>
	</tr>
	<tr>
		<td>상품이미지파일</td>
		<td><input type="file" name="file"></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" id="btnSave" value="저장">
		<input type="button" value="목록" onclick="location.href='${path}/shop/list.do' "></td>
	</tr>
</table>
</form>
</body>
</html>