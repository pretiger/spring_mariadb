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
		var userid=$("#userid");
		var name=$("#name");
		var email=$("#email");
		var passwd=$("#passwd");
		if(userid.val()==""){
			alert("Userid를 입력하세요!");
			userid.focus();
			return;
		}
		if(name.val()==""){
			alert("이름을 입력하세요!");
			name.focus();
			return;
		}
		if(email.val()==""){
			alert("이메일을 입력하세요!");
			email.focus();
			return;
		}
		if(passwd.val()==""){
			alert("비밀번호를 입력하세요!");
			passwd.focus();
			return;
		}
		document.form1.action="${path}/member/insert.do";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h3>회원가입</h3>
<form name="form1" method="post">
<table border="1" width="300px">
	<tr>
		<td>Userid</td>
		<td><input type="text" id="userid" name="userid"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" id="name" name="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" id="email" name="email"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="passwd" name="passwd"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="button" id="btnSave" value="저장"></td>
	</tr>
</table>
</form>
</body>
</html>