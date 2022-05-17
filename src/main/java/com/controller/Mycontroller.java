package com.controller;

import java.io.IOException;

import org.apache.catalina.connector.Response;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.model.LoginBean;

public class Mycontroller extends HttpServlet{	// controller 세팅

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client에서 get방식으로 요청할 경우 처리하는 블락
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client 에서 post방식으로 요청할 경우 처리하는 블락
			// client페이지에서 id와 password가 넘어오면 처리
		
		response.setContentType("text.html; charset = UTF-8");
			// response : client에 view 페이지로 전송할 contentType을 정의 
		String id = request.getParameter("id"); //넘겨온 id값 저장
		String password = request.getParameter("passwd"); //넘겨온 password값 저장
		
		LoginBean bean = new LoginBean();	// 객체 선언
		bean.setId(id);	// setter로 id값 주입
		bean.setPassword(password); // setter로 password값 주입
		
		request.setAttribute("bean", bean);  //"변수",bean(객체) => "bean"에 bean객체 자체를 던져줌
		
		boolean status = bean.validate();	// password가 "admin"일 때의 status
		
		if (status) {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_success.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("mvc_error.jsp");
			rd.forward(request, response);
			
			
		}
		
		
		
	}	
	
}
