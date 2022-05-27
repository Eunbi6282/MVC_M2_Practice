<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pass");
		
		
		if (user_id.equals("admin") && user_pw.equals("1234")) {
			// 쿠키 생성, 클라이언트 HDD에 쿠키의 값을 저장
			
			// 쿠키는 생성자에서 변수이름과 값을 넣는다. 
			Cookie cookie_id = new Cookie("userID", user_id);  // ("변수명", 들어갈 값)
			Cookie cookie_pw = new Cookie("userPW", user_pw);
			
			// 쿠키 설정
			cookie_id.setPath(request.getContextPath());  // 쿠키를 사용할 서버의 경로 설정
			cookie_id.setMaxAge(60*60); //3600초 -> 1시간
			
			cookie_pw.setPath(request.getContextPath());
			cookie_pw.setMaxAge(60*60); //3600초 -> 1시간
			
			
			// 쿠키를 클라이언트 하드디스크 안에 넣어야 함(reponse객체 사용)
			response.addCookie(cookie_id);
			response.addCookie(cookie_pw);
			
			out.println("쿠키 생성이 성공했습니다.");
			out.println(user_id + "님 환영합니다.");
			
		} else {
			out.println("쿠키 생성이 실패했습니다");
		}
			
				
	
	
	%>
</body>
</html>