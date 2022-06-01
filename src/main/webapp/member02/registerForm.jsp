<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="register.js"></script>
<meta charset="UTF-8">

<div id = "regForm" class = "box">
	<ul>
		
	  <li><label for="u_id">아이디</label>
          	<input id="u_id" name="u_id" type="email" size="20" maxlength="50" placeholder="example@kings.com" autofocus>
          	<button id="checkId">ID중복확인</button>
      <li><label for="u_pass">비밀번호</label>
          <input id="u_pass" name="u_pass" type="password" 
           size="20" placeholder="6~16자 숫자/문자" maxlength="16">
      <li><label for="repass">비밀번호 재입력</label>
          <input id="repass" name="repass" type="password" 
           size="20" placeholder="비밀번호재입력" maxlength="16">
      <li><label for="u_Name">이름</label>
          <input id="u_Name" name="u_Name" type="text" 
           size="20" placeholder="홍길동" maxlength="10">
      <li><label for="u_address">주소</label>
          <input id="u_address" name="u_address" type="text" 
           size="30" placeholder="주소 입력" maxlength="50">
      <li><label for="u_tel">전화번호</label>
          <input id="u_tel" name="u_tel" type="tel" 
           size="20" placeholder="전화번호 입력" maxlength="20">
      <li><label for="u_birthday">생년월일</label>
          <input id="u_birthday" name="u_birthday" type="date" 
           size="20" placeholder="전화번호 입력" maxlength="20">
      <li class="label2"><button id="process">가입하기</button>
          <button id="cancle">취소</button>
	</ul>
</div>
