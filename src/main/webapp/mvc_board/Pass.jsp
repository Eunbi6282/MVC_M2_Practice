<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script>
		function validateForm(form) {
			if (form.pass.value == "") {
				alert ("��й�ȣ�� �Է��ϼ���");
				form.pass.focus();
				return false;
			}
		}
	</script>



<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2> ���� ÷���� �Խ��� - �����ȣ ���� (pass)</h2>
	
	<form name ="WriteFrm" method = "post" action = "../mvc_board/pass.do" onsubmit = "return validateForm(this);">
		<input type = "hidden" name = "idx" value = "${param.idx }" />
		<input type = "hidden" name = "mode" value = "${param.mode }" />
		
		<table border = "1" width = "90%">
			<tr>
				<td> ��й�ȣ :  </td>
				<td> 
					<input type = "password" name = "pass" style = "width : 100px;" />
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<button type = "submit"> �����ϱ� </button>
					<button type = "reset"> RESET </button>
					<button type = "button" onclick = "location href = '../mvc_board/list.do';">
						��� �ٷΰ���
					</button>
				</td>
			</tr>
		
		</table>
	
	</form>
</body>
</html>