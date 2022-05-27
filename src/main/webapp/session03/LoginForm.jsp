<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
</head>
<body>

	<table border = "1" width = "90%">
		<tr>
			<td align = "center">
			<!-- 로그인에 따른 메뉴 변화
				// 로그인 성공일 땐 로그아웃, 로그인 실패일 땐 로그인창 띄우기
			 -->
			<% if(session.getAttribute("UserId") == null) { %>
			
				<a href = "../session02/LoginForm.jsp">로그인 </a>
				
			<%} else { %>
			
				<a href = "../session02/Logout.jsp"> 로그아웃</a>
				
			<%} %>
			</td>
		</tr>
	</table>


	<h2>로그인 페이지</h2>
	
	<span style = "color:red; font-size:1.2em;">
			<!-- 로그인 에러 메세지 출력 -->
		<%= request.getAttribute("LoginErrMsg") == null ? "": request.getAttribute("LoginErrMsg")
			// 조건이 true일 때 "" false 일 떄 request.getAttribute("LoginErrMsg")
				//LoginErrMsg 이 비어있다면 (로그인 성공인 경우), 값이 있다면 오류메세지 찍어주기
		%>
	</span>
	
	
	<% if (session.getAttribute("UserID") == null) { %>
	<script>
		function validateForm (form) {
			if(!form.user_id.value ) {
				alert("아이디를 입력해 주세요")
				return false;
			}
			
			if (form.user_pw.value=="") {
				alert("패스워드를 입력해 주세요");
				return false;
			}
		} 
	</script>
	<form action = "LoginProcess.jsp" method = "post" name = "LoginForm"
		onsubmit = "return validateForm(this);">
		 <p> 아이디 : <input type = "text" name = "user_id">
		 <p> 패스워드 : <input type = "password" name = "user_pw">
		 <p> <input type = "submit" value = "로그인하기">
	</form>
	<%} else {  // 로그인 상태	일 때%>	
		<table border = "1" color = "red">
			<tr>
				<td>
					<%= session.getAttribute("UserGrade") %> 
				</td>
			</tr>
		</table>
		
		<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다. <br>
		<a href = "Logout.jsp"> [로그아웃]</a>
	
	<% }%>
	
</body>
</html>