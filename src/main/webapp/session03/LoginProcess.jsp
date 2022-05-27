<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "membership03.MemberDAO" %>
<%@ page import = "membership03.MemberDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		// form에서 넘겨주는 변수값 받기
		String userID = request.getParameter("user_id");
		String userPwd = request.getParameter("user_pw");
		
		// DAO객체 호출, 회원 정보에 대한 값을 DTO로 넘겨받는다.
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberDTO(userID,userPwd); 
		// 아이디, 비번이 db의 값과 맞는지 확인
		
		// 등급dto
		//MemberDAO dao2 = new MemberDAO();
		//MemberDTO dto2 = dao2.getGrade(userID);
		dao.close();
		
		if (dto.getName() == null || dto.equals("")) {
			out.println("로그인 실패");
			request.setAttribute("LoginErrMsg", "로그인 오류입니다.");
			request.getRequestDispatcher("LoginForm.jsp").forward(request, response); 
				//setAttribute에서 에러메세지를 담아서 LoginForm.jsp에서 열림
		}else {
			out.println("로그인 성공");
			//session에 값할당
			session.setAttribute("UserID", dto.getId());
			session.setAttribute("UserName", dto.getName());
			session.setAttribute("UserGrade", dto.getGrade());
			response.sendRedirect("LoginForm.jsp");
		}
		
		
		
				
		
	
	
	
	
	
	
	%>
	
	
	
	
</body>
</html>