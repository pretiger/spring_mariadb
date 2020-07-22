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
	$("#btnLogin").click(function(){
		var userid=$("#userid");
		var passwd=$("#passwd");
		if(userid.val()==""){
			alert("관리자ID를 입력하세요!");
			userid.focus();
			return;
		}
		if(passwd.val()==""){
			alert("비밀번호를 입력하세요!");
			passwd.focus();
			return;
		}
		document.form1.action="${path}/member/passChk.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>관리자 로그인</h2>
<form name="form1" method="post">
<table border="1" width="300px">
	<tr>
		<td>관리자ID</td>
		<td><input type="text" id="userid" name="userid"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="passwd" name="passwd"></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="button" value="로그인" id="btnLogin">
		<c:if test="${param.message != null}"><div style="color: red;">${param.message}</div></c:if>
		<c:if test="${message != null}"><div style="color: red;">${message}</div></c:if>
		</td>
	</tr>
</table>
</form>
</body>
</html>