<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> session 변수의 특정 속성(필드)의 값을 삭제( removeAttribute() )</h2>
	
	<h4> ===== 세션 정보를 삭제하기 전 =====</h4>
	<%
		String user_id = (String)session.getAttribute("userID");
		String user_pw = (String)session.getAttribute("userPW");
		
		out.println("<p> 설정된 세션 이름 userID : " + user_id);
		out.println("<p> 설정된 세션 이름 userPW : " + user_pw);
	
		// session 객체에 저장된 특정 속성(필드)을 제거 
		// 장바구니에 넣은 값을 session에 저장 후 특정 값을 삭제할 때 사용.
		session.removeAttribute("userID");
	
	%>
	
	<h4> ===== 세션 정보를 삭제 후 =====</h4>
	
	<%
		user_id = (String) session.getAttribute("userID");
		user_pw = (String)session.getAttribute("userPW");
		
		out.println("<p> 설정된 세션 이름 userID : " + user_id);
		out.println("<p> 설정된 세션 이름 userPW : " + user_pw);
	
	%>
</body>
</html>