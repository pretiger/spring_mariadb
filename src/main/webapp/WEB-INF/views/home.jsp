<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="include/header.jsp" %>
</head>
<body>
<c:choose>
<c:when test="${sessionScope.admin_userid != null}">
<%@ include file="include/admin_menu.jsp" %>
</c:when>
<c:otherwise>
<%@ include file="include/menu.jsp" %>
</c:otherwise>
</c:choose>
<h1>
	Hello world!  
</h1>
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
