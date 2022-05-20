package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get������� ��û�� ���� �� �������� ó��
		
		// 1. DAO ��ü ���� (Model : ����Ͻ� ���� ó��)
		MVCBoardDAO dao = new MVCBoardDAO();	// ��ü ����� ���� Ŀ�ؼ�Ǯ(DBCP) ���� ���� => DAO���� super()�� �����߱� ���� DBConnPool�� ����Ǿ��ֱ� ������
		
		// 2. �信 ������ �Ű����� ����� �� ���� (Key, Value)
		Map<String, Object> map = new HashMap<String, Object>();
		String searchFiled = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		
		if(searchWord != null) {	// �˻�� �����Ѵٸ� map�� ���� ����
			map.put("searchField", searchFiled);
			map.put("searchWord", searchWord);
		}
		
		// �Խù� ���� �˾ƿ���(DAO�� selectCount
		int totalCount = dao.selectCount(map);
		// System.out.println("��ü ���ڵ� �� : " + totalCount);
		// System.out.println("List.do ��û�� Controller ������ �� ����");
		
		/* ����¡ ó�� �κ� start*/
			// web.xml���� ������ ���� ���Դ����ؿ���
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
			//System.out.println(pageSize);
			//System.out.println(blockPage);
		
			//���� ������ Ȯ��
			int pageNum = 1;
			String pageTemp = req.getParameter("pageNum"); // Parameter�� �Ѿ���� ���� ��� String, ����Ϸ��� int�� ��ȯ�ʿ� 
			if (pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp); // ���� ������� ���� �� �Ѿ�� ������ ������ ������ ��ȯ�ؼ� ������ �ִ´�. 
			}
			
			// ��Ͽ� ����� �Խù� ���� ���
			int start = (pageNum - 1) * pageSize + 1;	// ù �Խù� ��ȣ
			int end = pageNum * pageSize; //������ �Խù� ��ȣ
			
			// �� �������� ���� ������
			map.put("start", start);	// ("������", �� ��)
			map.put("end", end);
		
		/* ����¡ ó�� �κ� end*/
		
		// �Խù� ��� �޾ƿ���(DAO ��ü�� �۾��� ����)
			// DAO�� selectListPage()ȣ�� => return board �̹Ƿ� DTO���� �ҷ��� => boardLists�� ����� ���
				//DTO���� DB���� ������ ����ִ�. board�� DTO��ü�� ��� �ִ�. 
		List <MVCBoardDTO> boardLists = dao.selectListPage(map);  //map��start, end�� ���� �������
		dao.close(); //DB���� �ݱ�(DBConnPool�� ����ϰ� ����
		
		// ���������� ������ �Ű��������� �߰�
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvc_board/list.do"); // �ٷΰ��� HTML���ڿ�
		
		// ���������� ������ ���� ����
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// ���������� ������ ����, request ������ ������ �����͸� ���� �� List.jsp(��������)�� ������
		req.setAttribute("boardLists", boardLists); // ("������", ��) //DB���� Select�� �����
		req.setAttribute("map", map);
		req.getRequestDispatcher("/mvc_board/List.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Post������� ��û�� ���� �� �������� ó��
		
	}
	
}
