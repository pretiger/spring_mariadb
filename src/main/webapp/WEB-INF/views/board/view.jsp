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
	attachList();
	replyList();
	$("#btnAnswer").click(function(){
		location.href="${path}/board/answer.do/${dto.num}";
	});
	$("#btnSave").click(function(){
		var replyer="${sessionScope.userid}";
		var replytext=$("#replytext").val();
		var bnum="${dto.num}";
		var param={"replyer": replyer, "replytext": replytext, "bnum": bnum};
		$.ajax({
			type: "post",
			url: "${path}/board/insertReply.do",
			data: param,
			success: function(){
				replyList();
				$("#replytext").val("");
			}
		});
	});
	$("#btnUpdate").click(function(){
		var subject=$("#subject");
		var content=$("#content");
		if(subject.val()==""){
			alert("제목을 입력하세요!");
			subject.focus();
			return;
		}
		if(content.val()==""){
			alert("내용을 입력하세요!");
			content.focus();
			return;
		}
		document.form1.action="${path}/board/update.do";
		document.form1.submit();
	});
	$("#btnDelete").click(function(){
		if(confirm("삭제 하시겠습니까?")){
			document.form1.action="${path}/board/delete.do";
			document.form1.submit();
		}
	});
	$("#fileList").on("click", "a", function(){
		var that=$(this);
		that.parent("div").remove();
	})
	$("#uploadedFile").on("click", "span", function(){
		var that=$(this);
		console.log(that);
		$.ajax({
			url: "${path}/board/deleteFile",
			data: {filename: $(this).attr("data-src")},
			success: function(result){
				if(result == "deleted"){
					that.parent("div").remove();
				}
			}
		});
	});
});
function replyList(){
	$.ajax({
		url: "${path}/board/replyList.do/${dto.num}",
		success: function(result){
			$("#replyList").html(result);
		}
	});
}
function attachList(){
	$.ajax({
		url: "${path}/board/attachList.do/${dto.num}",
		success: function(result){
			$(result).each(function(){
				if("${sessionScope.userid}" == "${dto.writer}"){
					var str="<div><a href='${path}/board/downloadFile?filename="
						+this+"'>"+originalName(this)+"</a><span data-src="
						+this+"><a href='#'>  [삭제]</a></span></div>";
				}else{
					var str="<div><a href='${path}/board/downloadFile?filename="
						+this+"'>"+originalName(this)+"</a></div>";
				}
				$("#uploadedFile").append(str);
			});
		}
	});
}
function originalName(filename){
	return filename.substr(filename.indexOf("_")+1);
}
function addtag(){
	str="<div><a href='#'>[Delete]</a><input type='file' name='files'></div>";
	$("#fileList").append(str);
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판 상세내역</h2>
<form name="form1" method="post" enctype="multipart/form-data">
<table border="1" width="700px">
	<tr>
		<td width="10%">작성일</td>
		<td width="40%"><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td width="10%">조회수</td>
		<td width="40%">${dto.viewcount}</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3"><input type="text" id="subject" name="subject" size="60" value="${dto.subject}"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3"><textarea rows="3" cols="80" id="content" name="content">${dto.content}</textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3"><div id="uploadedFile"></div>
		<c:if test="${sessionScope.userid == dto.writer}">
			<span id="fileAttach" style="color: blue;" onclick="addtag()"><a href="#">[Add]	</a></span>	
			<input type="file" name="files" multiple><div id="fileList"></div>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center"><input type="hidden" name="num" value="${dto.num}">
			<input type="hidden" name="cnt" value="${dto.cnt}">
		<c:if test="${sessionScope.userid == dto.writer}">	
			<input type="button" id="btnUpdate" value="수정">
			<input type="button" id="btnDelete" value="삭제">
		</c:if>
		<c:if test="${sessionScope.userid != null}">
			<input type="button" id="btnAnswer" value="댓글">
		</c:if>
			<input type="button" value="목록" onclick="location.href='${path}/board/list.do'">
		</td>
	</tr>
</table>
</form>
<c:if test="${sessionScope.userid != null}">
<table width="700px">
	<tr>
		<td><textarea rows="3" cols="80" name="replytext" id="replytext" placeholder="덧글을 입력하세요!"></textarea>
			<button id="btnSave">저장</button></td>
	</tr>
</table>
</c:if>
<hr>
<div id="replyList"></div>
</body>
</html>