<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session객체에 변수의 값 할당</title>
</head>
<body>
	<%
		//client 폼에서 Id, Password 
	String user_id = request.getParameter("id");
	String user_pw = request.getParameter("passwd");
	
	//DB에서 가져온 값
	if(user_id.equals("admin") && user_pw.equals("1234")) {
		
		// 세션 변수에 값을 할당
		session.setAttribute("userID", user_id);
		session.setAttribute("userPW", user_pw);
		
		out.println("세션 설정이 성공되었습니다.");
		out.println(user_id + "님 환영합니다.");
	} else {
		out.println("세션 설정이 실패했습니다.");
	}
	
	
	%>



</body>
</html>