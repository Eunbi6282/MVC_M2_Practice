<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id = "bean" class = "dao.Calculator"/>
	<!--  Calculator 클래스를 bean객체로 사용 -->
	<!--  Calculator bean = new Calculator(); 와 같은 구문이다.  -->
	
	<%
		int m = bean.process(5);
		out.print(5 + "의 세제곱은 :" + m);
	%>
	<p><p>
	
	5의 세제곱은 : <%= m %>
</body>
</html>