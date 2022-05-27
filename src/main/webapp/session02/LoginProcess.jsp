<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "membership.MemberDTO" %>
<%@ page import = "membership.MemberDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// form에서 넘겨주는 변수값 받기
		String userId = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pw");
	
		// DAO객체호출 회원벙보에 대한 값을 DTO로 넘겨 받는다. 
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberDTO(userId, userPwd);
		dao.close();
		
		// 로그인 성공 여부에 따른 처리
		if (dto.getName() == null || dto.equals("")) {
			out.println("로그인 실패");
			request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
			request.getRequestDispatcher("LoginForm.jsp").forward(request, response); 
				//setAttribute에서 에러메세지를 담아서 LoginForm.jsp에서 열림
					// 에러 메서지는 Dispathcher로 넘겨야 넘어감!!!!!!
			
		}else {
			out.println("로그인 성공");
			session.setAttribute("UserId", dto.getId()); //dto.getId()의 값을 UserId에 넣기
			session.setAttribute("UserName", dto.getName()); 
			response.sendRedirect("LoginForm.jsp");
		}
	
		// request session
			// request -> 모든 사용자에게 적용  (모든 사용자가 로그인 실패하면 메세지 띄우기)
			// session -> 특정 사용자에게 적용	(로그인 성공 시 그 사용자의 값만 session에 저장)
	
	
	
	%>
</body>
</html>