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
	
	<!--  setter���� -->
	<jsp:setProperty name = "person" property ="id" value = "20220530" />
	<jsp:setProperty name = "person" property ="name" value = "���漱�ų�" />
	<p> ���̵� : <% out.println(person.getId()); %>
	<p> <p> <p>
	
	<p> ���̵� : <%= person.getId() %>
	<p> �̸� : <%= person.getName() %>
	
</body>
</html>