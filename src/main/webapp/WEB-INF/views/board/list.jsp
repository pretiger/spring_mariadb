<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
<style>
#previewTag{
	position: absolute;
	opacity: 0.8;
	padding: 5px;
	width: 250px;
	height: auto;
	background-color: yellow;
	visibility: hidden;
}
</style>
<script src="../include/js/getXMLHttpRequest.js"></script>
<script>
$(function(){
	$("#btnExcel").click(function(){
		location.href="${path}/board/excelDownload?currentPage=${page.currentPage}&searchkey="
			+$("#searchkey").val()+"&keyword="+$("#keyword").val();
	});
	$("#btnWrite").click(function(){
		location.href="${path}/board/write.do";
	});
	$("#btnSearch").click(function(){
		location.href="${path}/board/list.do?currentPage=${page.currentPage}&searchkey="
			+$("#searchkey").val()+"&keyword="+$("#keyword").val();
	});
});
function list(page){
	location.href="${path}/board/list.do?currentPage="+page+"&searchkey="
	+$("#searchkey").val()+"&keyword="+$("#keyword").val();
}
function preview(num){
	var xhr=XMLHttpRequest;
	var url="${path}/board/preview.do?num="+num;
	xhr=getXMLHttpRequest();
	xhr.open("get", url, true);
	xhr.send();
	xhr.onreadystatechange=function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				$("#previewTag").html(xhr.responseText);
			}else{
				console.log("Error status code : "+xhr.status);
			}
		}
	}
}
function show(){
	$("#previewTag").css("visibility", "visible");
}
function hide(){
	$("#previewTag").css("visibility", "hidden");
}
document.onmousemove=function(){
	$("#previewTag").css("marginTop", event.y+"px");
	$("#previewTag").css("marginLeft", event.x+"px");
}
</script>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>게시판 목록</h2>
<div id="previewTag">Content Preview</div>
<form name="form1">
<select name="searchkey" id="searchkey">
	<option value="all">전체</option>
	<option value="writer">작성자</option>
	<option value="subject">제목</option>
	<option value="content">내용</option>
</select>
<input type="text" id="keyword" name="keyword" value="${keyword}">
<input type="button" id="btnSearch" value="조회">
</form>
<table  width="700px">
	<tr>
		<td><button id="btnWrite">글쓰기</button></td>
		<td align="right"><button id="btnExcel">Excel로 저장</button></td>
	</tr>
</table>  
<table border="1" style="width: 700px;">
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
<c:forEach var="row" items="${list}">
	<tr>
		<td>${row.num}</td>
		<td>${row.name}</td>
		<td><a href="${path}/board/view.do/${row.num}" onmouseover="preview('${row.num}');show();" 
			onmouseout="hide();">
			<c:if test="${row.sublevel > 0}">
				<c:forEach begin="1" end="${row.sublevel}">&nbsp;&nbsp;</c:forEach></c:if>
			${row.subject}
			<c:if test="${row.cnt > 0}"><span style="color: red;">[${row.cnt}]</span></c:if></a>
		</td>
		<td><fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${row.viewcount }</td>
	</tr>
</c:forEach>
</table>
<table width="700px">
	<tr>
		<td align="center">
		<c:if test="${page.currentPage > 1}">
			<a href="javascript:list('1')">[처음]</a>
		</c:if>
		<c:if test="${page.currentBlock > 1}">
			<a href="javascript:list('${page.previousBlock}')">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${page.blockStart}" end="${page.blockEnd}">
		<c:choose>
			<c:when test="${page.currentPage == i}"><span style="color: red;">${i}</span></c:when>
			<c:otherwise><a href="javascript:list('${i}')">${i}</a></c:otherwise>
		</c:choose>
		</c:forEach>	
		<c:if test="${page.currentBlock < page.totalBlock}">
			<a href="javascript:list('${page.nextBlock}')">[다음]</a>
		</c:if>
		<c:if test="${page.currentPage < page.totalPage}">
			<a href="javascript:list('${page.totalPage}')">[끝]</a>
		</c:if>
		</td>
	</tr>
</table>
</body>
</html>