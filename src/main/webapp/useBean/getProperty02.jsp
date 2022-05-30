<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id = "person" class = "dao.Person" scope = "request" />
	<jsp:setProperty name = "person" property = "id" value = "20220606"/>
	<jsp:setProperty name = "person" property = "name" value = "현충일(휴일)" />
	
	<p> 아이디 : <jsp:getProperty name = "person" property = "id" />
	<p> 이름 : <jsp:getProperty name = "person" property = "name" />
	
	
	
</body>
</html>