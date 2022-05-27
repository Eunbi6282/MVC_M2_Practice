<%@page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie를 사용해서 아이디 저장하기</title>
</head>
<body>

	<%
		String loginId  = CookieManager.readCookie(request, "loginId");
		String cookieCheck = "";
		
		if(!loginId.equals("")) { // 값이 비어있지 않을 때
			cookieCheck = "checked";
		}
	
	
	%>
	<h2> 로그인 페이지</h2>
	<form action = "IdSaveProcess.jsp" method = "post" >
		<p> 아이디 : <input type = "text" name = "user_id" value = "<%= loginId %>">
			아이디 저장 : <input type = "checkbox" name = "save_check" value = "Y" <%= cookieCheck %> >
		<p> 패스워드 : <input type = "password" name = "user_pw">
		
		<p> <input type = "submit" value = "로그인">
	
	
	</form>
</body>
</html>