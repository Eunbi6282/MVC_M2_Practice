<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2> 세션 설정 확인</h2>
	
	<h4>======= web.xml에 설정된 세션 정보 출력 ========</h4>
	세션 유지 기간 설정 정보 : <%= session.getMaxInactiveInterval()/60%> 
	
	<p><p> 
	<h4>======= 세션 설정 정보 변경 =======</h4>
	<%
		session.setMaxInactiveInterval(5*60);  // 한시간으로 설정
		
		int time = session.getMaxInactiveInterval() / 60;  //분으로 출력
		
		out.println("세션 유효 시간 : " + time + "분");
	%>
	
</body>
</html>