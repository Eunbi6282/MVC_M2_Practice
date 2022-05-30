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
	<p> 아이디 : <%= person.getId() %>
	<p> 이름 : <%= person.getName() %>
	
	<% 
		// setter주입
		person.setId(20170033);
		person.setName("박은비");
	%>
	
	<p> <p> <p>
	<jsp:include page = "useBean03.jsp"/>
	
</body>
</html>