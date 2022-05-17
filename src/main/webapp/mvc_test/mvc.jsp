<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "my.do">  
	<!-- my.do를 요청하면 Mycontroller로 가도록 web.xml으로 가도록 Controller 설정 해둠 -->
		<p> 아이디 : <input type = "text" name = "id">
		<p> 비밀번호 : <input type = "password" name = "passwd">
		<p> <input type = "submit" value = "전송">
	
	
	</form>
</body>
</html>