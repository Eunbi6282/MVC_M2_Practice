<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon2.LogonDAO" %>
<%@ page import = "java.sql.Date" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<meta charset="UTF-8">

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id = "member" class = "logon2.LogonDTO">
	<jsp:setProperty name="member" property="*"/>
</jsp:useBean>

<%

	// 폼으로부터 넘어오지 않는 데이터인 가입날짜를 직접 데이터 저장빈에 세팅
	member.setR_date(new Date(System.currentTimeMillis()));

	LogonDAO manager = LogonDAO.getInstance();
	manager.insertMember(member);  // setProperty를 통해 값들 다 저장했음!!
%>

