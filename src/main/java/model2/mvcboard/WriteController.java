package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvc_board/write.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get��� ��û ó��
		//List.jsp(�� ������)���� �۾��⸦ Ŭ������ �� �۾��� �������� (Write.jsp)
		// ���������� �̵�
		request.getRequestDispatcher("/mvc_board/Write.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post��� ��û ó��
		// Form(Write.jsp)���� �Ѿ���� ������ ���� ó��
		
		// form���� ������ �����ϹǷ� cos.jar ���̺귯���� ��ü ������ ������ ���� �޾ƾ� �Ѵ�.
		
		// 1. ���� ���ε� ó��
			//saveDirectory ������ ���ε��� ������ ������ ������ �������� ��θ� ����
			String saveDirectory = request.getServletContext().getRealPath("/Uploads");
			
			//maxPostSize : ���ε��� �ִ� �뷮 (web.xml <== 1MB)
			ServletContext application = getServletContext(); //�̰� ���� ������ xml������ �ִ� �������� �ҷ��� �� �ִ�.
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// ���� ���ε� ��ü ����
			MultipartRequest mr = FileUtil.uploadFile(request, saveDirectory, maxPostSize);
			
			// ��ü ���� ���� �� ó���� ����
			if(mr == null) { //null �̸� ��ü�������� (1MB�̻��� ��� �̹����� ���� ��)
				JSFunction.alertLocation(response, "÷�� �뷮�� �ʰ��Ǿ����ϴ�.", "../mvc_board/write.do");
				return;
			}
		
		//2. ���� ���ε� �� ó�� (form�� ������ ó��)
			// ������ �Ѱܹ��� ���� �޾Ƽ� DTO(VO)�� Setter������ �ϰ� DAO�� Insert�޼��忡 ������
			MVCBoardDTO dto = new MVCBoardDTO();
			dto.setName(mr.getParameter("name"));  
				//mr.getParameter("name");	// ������ �Ѱܹ���
			dto.setTitle(mr.getParameter("title"));
			dto.setContent(mr.getParameter("content"));
			dto.setPass(mr.getParameter("pass"));
			
			// ���� ���� �̸��� ���� ���� �̸� ����
			String fileName = mr.getFilesystemName("ofile"); //clinet�� ÷�������� ������ �ּ�
			if (fileName != null) {	// ÷�� ������ ������� ������ 
				
				// ���ο� ���� �̸����� �����ؼ� ������ ������ (������ �ش������� ������ ��찡 �����Ƿ�)
				String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
					System.out.println();
				
				// Ȯ���ڸ� �߶� ����
				String ext = fileName.substring(fileName.lastIndexOf("."));
					System.out.println("ext" + ext); // �ּ�ó��
					
				// ������ ������ �����̸� ����
				String newFileName = now + ext;
					System.out.println(newFileName);
				
				// ���ϸ� ����
				File oldFile = new File(saveDirectory + File.separator + fileName);
				File newFile = new File(saveDirectory + File.separator + newFileName);
					System.out.println("oldFile : " + oldFile);
					System.out.println("newFile : " + newFile);
				oldFile.renameTo(newFile);
				
				
				// DTO�� Setter���� (���� : ������ ���ε��� ��쿡��)
				dto.setOfile(fileName); //���� ���� �̸�
				dto.setSfile(newFileName); // ������ ����� ���� �̸�
				
			}
			
			//DTO�� ��ü�� DAO�� insertWrite(dto) �޼��带 ȣ���ؼ� DB�� ����
			MVCBoardDAO dao = new MVCBoardDAO();
			int result = dao.insertWrite(dto);
			
			// ��ü ���� �޼��� ȣ�� (rs, stmt, psmt, con ��� ����)
			dao.close();
			
			// �۾��� ������ �� �̵��� ������
			if(result == 1) { 	//result ==1 �۾��� ����
				response.sendRedirect("../mvc_board/list.do"); // ������ �� list�������� �̵�
			}
			// �۾��� ������ �� �̵��� ������
			if (result == 0) {  // �۾��� ����
				response.sendRedirect("../mvc_board/write.do");
			}
			
			
			
	}
}
