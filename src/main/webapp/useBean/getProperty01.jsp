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
	<p> 아이디 : <jsp:getProperty name = "person" property = "id" />
		<!--  person 객체의 아이디, 이름을 getter를 총해서  -->
	<p> 이름 : <jsp:getProperty name="person" property="name" />
	
	<p><p><p>
	<p> 아이디 : <%= person.getId() %>
	<p> 이름 : <%= person.getName() %>
	
	<p><p><p>
	<p> 아이디 : <% out.println(person.getId());%>
	<p> 이름 : <% out.println(person.getName());%>
</body>
</html>