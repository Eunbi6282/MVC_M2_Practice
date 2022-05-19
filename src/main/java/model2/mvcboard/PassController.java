package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PassController 정상작동");
		String mode = req.getParameter("mode");
		System.out.println("mode변수의 값 :" + mode);
		
		
		// view 페이지(/mvc_board/pass.jsp)로 변수값 넘김
		// mode:edit <== 글 수정, mode:delete <== 글 삭제
		req.setAttribute("mode", req.getParameter("mode"));  //get방식으로 mode값이 들어옴 => 그걸 "mode"변수에 담는다.
		req.getRequestDispatcher("/mvc_board/pass.jsp").forward(req, resp);
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
