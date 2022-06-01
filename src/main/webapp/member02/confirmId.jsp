<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon2.LogonDAO" %>
<% request.setCharacterEncoding("UTF-8");  %>

<%
	// id는 사용자가 회원가입을 하기위해서 입력한 아이디 폼에서 받아옴
	String id = request.getParameter("u_id");
	
	//DB처리빈인 LogonDAO클래스의 객체 얻어내기
	LogonDAO manager = LogonDAO.getInstance();
	
	int check = manager.confirmId(id);
	// 사용자가 입력한 id값을 가지고 LogonDAO클래스의 condirmID메서드 호출
	// 중복아이디 체크 , 메서드 실행 결과로 check에는 1또는 -1값이 리턴된
	out.println(check);

%>