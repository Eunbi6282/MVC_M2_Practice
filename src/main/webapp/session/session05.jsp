<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> 세션에 저장된 모든 속성(필드)를 제거 (로그아웃) : session.invalidate()</h2>
	<h4> ===== 세션 삭제 전 =====</h4>
	<%
		String user_id = (String)session.getAttribute("userID");
		String user_pw = (String)session.getAttribute("userPW");
		
		out.println("<p> 설정된 세션 이름 userID : " + user_id);
		out.println("<p> 설정된 세션 이름 userPW : " + user_pw);
		
		if(request.isRequestedSessionIdValid() == true) {
			out.println("세션이 유효합니다.");
		}else {
			out.println("세션이 유효하지 않습니다.");
		}
		
		session.invalidate(); // 세션의 모든 필드의 값을 한꺼번에 제거 (// logout)
	%>
	
	<h4> ===== 세션 삭제 후(모든 세션 변수 삭제) =====</h4>
	
	<%
	
		if(request.isRequestedSessionIdValid() == true) {
			out.println("세션이 유효합니다.");
		}else {
			out.println("세션이 유효하지 않습니다.");
		}
	
	%>
	
</body>
</html>