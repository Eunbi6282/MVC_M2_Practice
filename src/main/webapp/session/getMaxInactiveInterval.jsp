<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2> ���� ���� Ȯ��</h2>
	
	<h4>======= web.xml�� ������ ���� ���� ��� ========</h4>
	���� ���� �Ⱓ ���� ���� : <%= session.getMaxInactiveInterval()/60%> 
	
	<p><p> 
	<h4>======= ���� ���� ���� ���� =======</h4>
	<%
		session.setMaxInactiveInterval(5*60);  // �ѽð����� ����
		
		int time = session.getMaxInactiveInterval() / 60;  //������ ���
		
		out.println("���� ��ȿ �ð� : " + time + "��");
	%>
	
</body>
</html>