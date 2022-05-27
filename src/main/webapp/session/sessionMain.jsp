<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2> 세션 설정 확인</h2>
	
	<%
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); //날짜 표시 형식
		long creationTime = session.getCreationTime(); //Uinx시간으로 출력(1970.01.01)
		String creationTimeStr = dateFormat.format(new Date(creationTime));
		
		long lastTime = session.getLastAccessedTime();//마지막 요청 시간
		String lastTimeStr = dateFormat.format(new Date(lastTime));
	
	%>
	
	<ul>
		<li> 세션 유지 기간 설정 정보 : <%= session.getMaxInactiveInterval()/60%> (분)</li>
		<li> 세션 아이디 : <%= session.getId() %></li>
		<li> 최초 세션이 생성된 시간 : <%= creationTimeStr %></li>
			<!-- Unix 시간으로 출력 : 1970.01.01 현재 시간까지의 (초) -->
		<li> 마지막 요청 시간 : <%= lastTimeStr %> </li>
	</ul>
</body>
</html>