var status = true;
$(document).ready(function() {
	// [회원가입] 버튼을 클릭하면 자동실행
	$("#register").click(function() { // [회워가입] 버튼 클릭
		// 회원가입폼 register.form 로드
		// id 속성값이 main_auth인 영역에 로드
		$("#main_auth").load("registerForm.jsp");
	});
	
	//[로그인]버튼을 클릭하면 자동실해
	// 입력한 아이디와 비밀번호를 가지고 loginPro.jsp실행
	$("#login").click(function() {
		checkIt();  // 입력폼에 입력한 상황 체크
		
		if(status) { // checkIt()의 결과가 true로 넘어왔다면
			// 입력된 사용자의 아이디와 비밀번호를 얻어냄
			var query = {u_id:$("#u_id").val(),
						u_pass:$("#u_pass").val()};
			
			$.ajax({
				type: "POST",
				url: "loginPro.jsp",
				data:query,
				success: function(data) {
					if(data == 1) // 로그인 성공
						$("#main_auth").load("loginForm.jsp");
					else if(data == 0) { // 비밀번호 틀림
						alert("비밀번호가 맞지 않습니다.");
						$("#id").val("");
			    		 $("#passwd").val("");
			    	  	 $("#id").focus();
					}
				}
			});
		}
	});	
	
});

function checkIt() {
	status = false;
	if(!$.trim($("#u_id").val())) {
		alert("아이디를 입력하세요");
		$("#u_id").focus();
		status = false;
		return false;
	}
	
	if(!$.trim($("#u_pass").val())) {
		alert("비밀번호를 입력하세요");
		$("#u_pass").focus();
		status = false;
		return false;
	}
}