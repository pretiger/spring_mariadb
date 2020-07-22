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
		var name=$("#name");
		var email=$("#email");
		var passwd=$("#passwd");
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
		document.form1.action="${path}/member/update.do";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제 하시겠습니까?")){
			location.href="${path}/member/delete.do?userid=${dto.userid}";
		}
	});
});
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h3>회원정보 수정/삭제</h3>
<form name="form1" method="post">
<table border="1" width="300px">
	<tr>
		<td>Userid</td>
		<td><input type="text" name="userid" value="${dto.userid}" readonly></td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" id="name" name="name" value="${dto.name}"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" id="email" name="email" value="${dto.email}"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" id="passwd" name="passwd" value="${dto.passwd}"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnDelete" value="삭제">
			<input type="button" value="목록" onclick="location.href='${path}/member/list.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>