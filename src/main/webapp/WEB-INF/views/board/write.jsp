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
		var subject=$("#subject");
		var content=$("#content");
		if(subject.val()==""){
			alert("Could you write down subject");
			subject.focus();
			return;
		}
		if(content.val()==""){
			alert("Could you write down content");
			content.focus();
			return;
		}
		document.form1.action="${path}/board/insert.do";
		document.form1.submit();
	});
	$("#fileList").on("click", "a", function(){
		var that=$(this);
		that.parent("div").remove();
	})
});
function addtag(){
	str="<div><a href='#'>[Delete]</a><input type='file' name='files'></div>";
	$("#fileList").append(str);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판 글쓰기</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table border="1" width="700px">
	<tr>
		<td>제목</td>
		<td><input type="text" id="subject" name="subject" size="60"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea rows="3" cols="80" id="content" name="content"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td><span id="fileAttach" style="color: blue;" onclick="addtag()"><a href="#">[Add]
			</a></span>	<input type="file" name="files"><div id="fileList"></div></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" id="btnSave" value="저장"></td>
	</tr>
</table>
</form>
</body>
</html>