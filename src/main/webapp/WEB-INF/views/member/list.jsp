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
	$("#btnWrite").click(function(){
		location.href="${path}/member/write.do";
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>회원 관리</h2>
<button id="btnWrite">회원가입</button>
<table border="1" style="width: 700px">
	<tr>
		<th>Userid</th>
		<th>이름</th>
		<th>이메일</th>
		<th>가입일</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.userid}</td>
		<td>${row.name}</td>
		<td><a href="${path}/member/edit.do?userid=${row.userid}">${row.email}</a></td>
		<td><fmt:formatDate value="${row.join_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
</c:forEach>
</table>
</body>
</html>