package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.protocol.Resultset;
import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/mvc_board/edit.do")
public class EditController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��й�ȣ ���� �� ������ �Ϸ�Ǹ� idx�� �ش��ϴ� ���ڵ带 dto�� ��Ƽ� Edit.jsp 
		//Get������� ��û�� �޾Ƽ� Client�� �ѱ�� ������ �Ҵ�޾Ƽ� ���������� �ѱ�
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);	//idx�� �ش��ϴ� ���ڵ带 �Ѱܹ޾� DTO�� ����
		
		System.out.println("idx : " + idx);
		req.setAttribute("dto", dto); // "dto"�� dto�� ��Ƽ� ���������� �Ѱ���
		req.getRequestDispatcher("/mvc_board/Edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Edit.jsp���� ������ ������ �޾Ƽ� DB�� ����
		
		// 1. ���� ���ε� ó��
			//���ε� ���丮�� �������� ��θ� Ȯ���ؾ� �Ѵ�.
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
			// C:\Users\82102\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\MVC_M2\Uploads
				// => ������ ������ ���ε��� �������� ������
			
			// (1) ���ε��� ������ �ִ� �뷮 Ȯ��(web.xml�� ������ ������: maxPostSize (1MB)
			ServletContext application = this.getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// (2) ���� ���ε� <<���߿� ó��>>
			 MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			 	//MultipartRequest => cos.jar ��ü
			
			 if(mr == null) {	// null -> ���� ���ε� ����
				 JSFunction.alertBack(resp, "÷������ �뷮�� �ʰ��Ǿ����ϴ�.");
				 return;
			 }
			 
		// 2. ���� ���ε� �� ó��
			 // request ��ü�� �ƴ϶� MultipartRequest��ü���� Form�� ���� ���� �޴´�. 
			 // ���ε� ���̺귯������ Form�� ���� ���� �޴� �޼��� �̸��� �ٸ� �� �ִ�.
		String idx = mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//��й�ȣ : Session ������ ������ �����´�.
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
		//DTO�� �Ѱܹ��� �������� ���� (Client Form ==> DTO ==> DAO�� ����)
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		// ������ �Ѿ������ Ȯ��
		System.out.println("DTO ��ü�� ����� �� �ҷ����� ============");
		System.out.println(dto.getIdx());
		System.out.println(dto.getName());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getPass());
		
		
		
		// dto ��ü�� Ofile, Sfile�� ���ε� ��ο� �ش� ���ϸ��� �����ϴ� �ܿ� ó��
		// ���� ���ϸ�� ����� ���� �̸� ����
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {	// ÷�������� Uploads ������ �����ϴ� ��� ���� �̸��� �����ؼ� ����
			
			// ���ο� ���ϸ� ����
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date()); // ��¥�� ó��
			String ext = fileName.substring(fileName.lastIndexOf(".")); //.�ڿ��ִ� Ȯ���ڱ����� ������ ��
			String newFileName = now + ext;
			
			System.out.println("now : " + now);
			System.out.println("ext : " + ext);
			System.out.println("newFileName : " + newFileName);
			
			// ���ϸ� ����
			File oldFile = new File (saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			
			oldFile.renameTo(newFile);
			
			// ������ ���� DTO�� ����
			dto.setOfile(fileName);	// ���� ���� �̸�
			dto.setSfile(newFileName);	// ���ο� ���� �̸� (������ ����� ���� �̸�)
			
			//������ ���� ����
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
			
		} else { // ÷�������� �������� ������ ������ �̸� ����
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		// DB�� ���� ������ �ݿ� (DTO�� ����� ���� DAO�� �޼��忡 �Ű������� ����)
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto); //result == 1: update ����, result == 0: update ����
		dao.close();
		
		// ���� ���� vs ����
		if(result == 1) { // ����
			session.removeAttribute("pass");
			resp.sendRedirect("../mvc_board/view.do?idx=" + idx );
			
		}else {
			JSFunction.alertLocation(resp, "��й�ȣ ������ �ٽ� ���ּ���", "../mvc_board/view.do?idx=" + idx);
		}
		
	}
	
}
