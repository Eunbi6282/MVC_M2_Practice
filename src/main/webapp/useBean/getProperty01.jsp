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
	<!-- Getter -->
	<p> ���̵� : <jsp:getProperty name = "person" property = "id" />
		<!--  person ��ü�� ���̵�, �̸��� getter�� ���ؼ�  -->
	<p> �̸� : <jsp:getProperty name="person" property="name" />
	
	<p><p><p>
	<p> ���̵� : <%= person.getId() %>
	<p> �̸� : <%= person.getName() %>
	
	<p><p><p>
	<p> ���̵� : <% out.println(person.getId());%>
	<p> �̸� : <% out.println(person.getName());%>
</body>
</html>