<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>게시판 상세</h2>
	<table border="1">
		<tr>
			<td>제목</td>
			<td>${data.subject }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${data.article }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${data.author }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${data.regdate }
		</tr>
		<tr>			
			
		</tr>
	</table>
</body>
</html>