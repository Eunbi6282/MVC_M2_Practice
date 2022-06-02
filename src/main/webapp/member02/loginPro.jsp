<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon2.LogonDAO" %>
<% request.setCharacterEncoding("UTF-8"); %>

<%
	// 사용자가 입력한 아이디와 비밀번호	
		// 폼에서 넘겨온 값 받아오기
	String id = request.getParameter("u_id");
	String passwd = request.getParameter("u_pass");
	
	// System.out.print(id); 잘 넘어옴
	LogonDAO manager = LogonDAO.getInstance();
	int check = manager.userCheck(id, passwd); // 사용자 인증처리 메서드
	
	// 사용자 인증에 성공시 세션속성을 설정
	out.println("사용자 인증 성공");
	if (check == 1 ) {
		session.setAttribute("u_id", id);  // id값을 "u_id"변수에 넣기
	}
	out.println(check); // 처리 결과를 반환


%>    
 
