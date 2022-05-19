package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PassController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("PassController �����۵�");
		String mode = req.getParameter("mode");
		System.out.println("mode������ �� :" + mode);
		
		
		// view ������(/mvc_board/pass.jsp)�� ������ �ѱ�
		// mode:edit <== �� ����, mode:delete <== �� ����
		req.setAttribute("mode", req.getParameter("mode"));  //get������� mode���� ���� => �װ� "mode"������ ��´�.
		req.getRequestDispatcher("/mvc_board/pass.jsp").forward(req, resp);
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
