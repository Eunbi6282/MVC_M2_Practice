<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "com.model.LoginBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 성공 페이지</title>
</head>
<body>
	<p> 로그인 성공했습니다.
	
	<p>
	<% 
		LoginBean bean = (LoginBean) request.getAttribute("bean"); // 임포트 필요
			// Mycontroller => request.setAttribute("bean", bean);  //"변수",bean(객체) => "bean"에 bean객체 자체를 던져줌
			// "bean" 변수의 값 가져옴
			// set으로 값 설정, get으로 값 가져옴
			
		out.println("아이디 : " + bean.getId()); //bean 의 id가지고 오기
		
	%>
</body>
</html>