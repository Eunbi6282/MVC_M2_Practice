<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업 쿠키를 처리하는 페이지 </title>
</head>
<body>


<%
	//main의 input 의 id 가 inactiveToday, value가 1 인 값을 받아와서
	String chkVal = request.getParameter("inactiveToday");
//1
if(chkVal != null && chkVal.equals("1")){
	Cookie cookie = new Cookie("popupClose","off"); //쿠키생성
	cookie.setPath(request.getContextPath()); //경로설정.(/MVC_M2)
	cookie.setMaxAge(60*60*24); //쿠키가 저장되는 기간 : 하루
	response.addCookie(cookie); //client hDD에 저장
	out.println("쿠키: 하루동안 열리지 않음(쿠키저장성공')");
}


%>

</body>
</html>