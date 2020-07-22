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
        var frm = $("#frm").serialize(); // 해당하는 frm을 serialize를 해줍니다. ajax로 데이터를 보내기위해서 하는 작업입니다.
        var userid = $("#userid").val(); // id값은 기본키이자 바뀌면안되는것이고 id값으로 조건을 줄꺼라서 고유 id 값을 받아옵니다.
        $.ajax({
            type : "post", // post방식으로 전송
            url : "${path}/popup/update.do", // controller로 보낼 url
            data : frm, // data로는 위에서 serialize한 frm을 보냅니다.
            async : false, // 전역변수 사용을 위해서 설정해준다
            dataType : "json", // serialize하면 json형태로 값을 보내줘야합니다.
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", // 인코딩 설정
            success : function(data){
            	console.log(data);
            	alert(data);
                $(opener.document).find("tr[id="+userid+"]>td[id=userid]").text(data.userid); //  부모창에서 현재 변경될 td를 찾은 후 값을 변경해줍니다.
                $(opener.document).find("tr[id="+userid+"]>td[id=name]").text(data.name); // 부모창에서 현재 변경될 td를 찾은 후 값을 변경해줍니다.
                $(opener.document).find("tr[id="+userid+"]>td[id=email]").text(data.email); // 부모창에서 현재 변경될 td를 찾은 후 값을 변경해줍니다.
                $(opener.document).find("tr[id="+userid+"]>td[id=date]").text(data.date); // 부모창에서 현재 변경될 td를 찾은 후 값을 변경해줍니다.
                self.close(); // 변경 후 자식 창을 받아줍니다.
            }
        });
    });
});
</script>
</head>
<body>
<h3>회원정보 수정</h3>
<form name="form1" method="post" id = "frm">
<table border="1" width="300px">
	<tr>
		<td>Userid</td>
		<td><input type="text" id="userid" name="userid" value="${dto.userid}" readonly></td>
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
		<td colspan="2"><input type="button" id="btnUpdate" value="수정"></td>
	</tr>
</table>
</form>
</body>
</html>