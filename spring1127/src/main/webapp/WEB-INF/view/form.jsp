<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form action="send.do" method="post">
�̸� : <input type="text" name="name"><br>
�̸��� : <input type="text" name="email"><br>
��ȣ : <input type="text" name="no"><br>
��� : <input type="checkbox" name="hobby" value="book">����
		<input type="checkbox" name="hobby" value="movie">��ȭ
		<input type="checkbox" name="hobby" value="game">����
<input type="submit" value="����">
</form>
</body>
</html>