package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvc_board/view.do")
public class ViewController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get요청시 처리
		//System.out.println("ViewController 작동 잘 됨(Get요청)");
		
		// 게시물 정보 불러오기 (1. 조회수 증가, 2. 게시물 정보 가져오기)
		MVCBoardDAO dao = new MVCBoardDAO();
		
		String idx = req.getParameter("idx");
		
		// 조회수 증가
		dao.updateVisitCount(idx);
		
		// 게시물의 자세한 정보값 가져오기
		MVCBoardDTO dto = dao.selectView(idx);
		dao.close(); // 객체 반납
		
		//DB의 content 컬럼의 \r\n (엔터)을 "<br />" 태그로 변환해야 한다.
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));
		
		// 게시물 (dto) 객체를 view페이지로 전달하기 위한 변수값 저장
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/mvc_board/View.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post요청시 처리
	}
	
}
