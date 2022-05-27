<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> session 객체의 변수에 할당된 값 가져오기</h2>
	
	<%
		//Session 객체의 값을 가져올 떄 Object 타입이므로 다운캐스팅 필요
		
		// 모든 페이지에서 세션의 변수에 들어간 값을 가져와서 null일 경우 : 로그인 안된 상태
			// getAttribute <== 세션 객체에 담긴 하나의 변수의 값을 가져올 떄
			String user_id = (String)session.getAttribute("userID");
			String user_pw = (String)session.getAttribute("userPW");
			
			out.println("<p> 설정된 세션의 속성값1 : " + user_id);
			out.println("<p> 설정된 세션의 속성값2 : " + user_pw);
			
			out.println("세션 설정이 성공되었습니다.");
			out.println(user_id + "님 환영합니다.");
		
	%>
</body>
</html>