<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Bean의 Getter로 RAM의 값을 호출</title>
</head>
<body>
	<jsp:useBean id = "person" class = "dao.Person" scope = "request" />
	
	<p> 아이디 : <%= person.getId() %>
	<p> 이름 : <%= person.getName() %>
</body>
</html>