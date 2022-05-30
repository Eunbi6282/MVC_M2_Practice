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
	
	<!--  setter주입 -->
	<jsp:setProperty name = "person" property ="id" value = "20220530" />
	<jsp:setProperty name = "person" property ="name" value = "지방선거날" />
	<p> 아이디 : <% out.println(person.getId()); %>
	<p> <p> <p>
	
	<p> 아이디 : <%= person.getId() %>
	<p> 이름 : <%= person.getName() %>
	
</body>
</html>