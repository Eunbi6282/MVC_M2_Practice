var status = true;

$(document).ready(function() {
	
	//[ID]증복확인 버튼을 클릭하면 자동실행
	// 입력한 아이디 값을 가지고 confirmId.jsp실행
	$("#checkId").click(function(){
		if($("#u_id").val()) {
			// 아이디를 입력하고 중복확인 버튼을 클릭한 경우
			var query = {u_id:$("#u_id").val()};
			
			$.ajax ({
				type:"post",  // 요청방식
				url:"confirmId.jsp", // 요청페이지
				data:query, // 파라미터
				success:function(data) { // 요청페이지 처리에 성공시
					if(data == 1) { // 사용할 수 없는 아이디
						alert("중복된 아이디이므로 사용할 수 없습니다.");
						$("#u_id").val("");
					}else if (data == -1) { // 사용할 수 있는 아이디
						alert ("사용할 수 있는 아이디입니다.");
					}
				}
			});
		}else {	// 이이디를 입력하지 않고 [ID중복확인] 버튼을 클릭한 경우
			alert("사용할 아이디를 입력");
			$("#u_id").focus();
		}
	});
	
	
	
	
	// [가입하기] 버튼을 클릭하면 자동실행
	// 사용자가 가입폼인 registerForm 페이지에 입력한 내용을 가지고 registerPro 페이지 실행
	$("#process").click(function() {
		checkIt();  // 입력폼에 입력한 상황 체크
		
		if(status) {
			var query = {u_id:$("#u_id").val(),
						u_pass:$("#u_pass").val(),
						u_Name:$("#u_Name").val(),
						u_address:$("#u_address").val(),
						u_tel:$("#u_tel").val(),
						u_birthday:$("#u_birthday").val()
			};
			$.ajax({
				type:"post",
				url:"registerPro.jsp",
				data:query,
				success:function(data){
					window.location.href = 'main.jsp';
				}
			});
		}
	});
	
	//[취소]버튼을 클릭하면 자동실행
	$("#cancle").click(function() {
		window.location.href = 'main.jsp';
	});
	
	
});

// 사용자가 입력폼에 입력한 상황을 체크 (checkIt) 
function checkIt(){
	status = true;
	
	// 아이디 미입력시 수행
	if(!$("#u_id").val()) {
		alert("아이디를 입력하세요");
		$("#u_id").focus();
		status = false;
		return false; // 사용자가 서비스를 요청한 시점으로 돌아감
	}
	
	// 비밀번호 미입력시 수행
	if(!$("#u_pass").val()) {
		alert("비밀번호를 입력하세요");
		$("#u_pass").focus();
		status = false;
		return false; // 사용자가 서비스를 요청한 시점으로 돌아감
	}
	
	// 재입력비번 틀렸을 시 실행
	if($("#u_pass").val() != $("#repass").val()) {
		alert("비밀번호를 동일하게 입력해주세요");
		$("#repass").focus();
        status = false;
        return false;
	}
	
	if(!$("#u_Name").val()) {//이름을 입력하지 않으면 수행
        alert("사용자 이름을 입력하세요");
        $("#u_Name").focus();
        status = false;
        return false;
    }
    
    if(!$("#u_address").val()) {//주소를 입력하지 않으면 수행
        alert("주소를 입력하세요");
        $("#u_address").focus();
        status = false;
        return false;
    }
    
    if(!$("#u_tel").val()) {//전화번호를 입력하지 않으면 수행
        alert("전화번호를 입력하세요");
        $("#u_tel").focus();
        status = false;
        return false;
    }  
    
    if(!$("#u_birthday").val()) {//생년월일을 입력하지 않으면 수행
        alert("생년월일을 입력하세요");
        $("#u_birthday").focus();
        status = false;
        return false;
    } 
}