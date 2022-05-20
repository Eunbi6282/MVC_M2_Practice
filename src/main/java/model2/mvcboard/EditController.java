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
		// 비밀번호 검증 후 검증이 완료되면 idx에 해당하는 레코드를 dto에 담아서 Edit.jsp 
		//Get방식으로 요청을 받아서 Client가 넘기는 변수를 할당받아서 뷰페이지로 넘김
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);	//idx에 해당하는 레코드를 넘겨받아 DTO에 저장
		
		System.out.println("idx : " + idx);
		req.setAttribute("dto", dto); // "dto"에 dto를 담아서 뷰페이지로 넘겨줌
		req.getRequestDispatcher("/mvc_board/Edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Edit.jsp에서 수정될 내용을 받아서 DB에 적용
		
		// 1. 파일 업로드 처리
			//업로드 디렉토리에 물리적인 경로를 확인해야 한다.
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
			// C:\Users\82102\eclipse-workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\MVC_M2\Uploads
				// => 서버의 파일을 업로드할 물리적인 절대경로
			
			// (1) 업로드할 파일의 최대 용량 확인(web.xml의 설정을 가져옴: maxPostSize (1MB)
			ServletContext application = this.getServletContext();
			int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
			
			// (2) 파일 업로드 <<나중에 처리>>
			 MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
			 	//MultipartRequest => cos.jar 객체
			
			 if(mr == null) {	// null -> 파일 업로드 실패
				 JSFunction.alertBack(resp, "첨부파일 용량이 초과되었습니다.");
				 return;
			 }
			 
		// 2. 파일 업로드 외 처리
			 // request 객체가 아니라 MultipartRequest객체에서 Form의 변수 값을 받는다. 
			 // 업로드 라이브러리마다 Form의 변수 값을 받는 메서드 이름이 다를 수 있다.
		String idx = mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//비밀번호 : Session 변수에 값에서 가져온다.
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		
		//DTO에 넘겨받은 변수값을 저장 (Client Form ==> DTO ==> DAO에 전달)
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		// 변수값 넘어오는지 확인
		System.out.println("DTO 객체에 저장된 값 불러오기 ============");
		System.out.println(dto.getIdx());
		System.out.println(dto.getName());
		System.out.println(dto.getTitle());
		System.out.println(dto.getContent());
		System.out.println(dto.getPass());
		
		
		
		// dto 객체의 Ofile, Sfile은 업로드 경로에 해당 파일명이 존재하는 겨우 처리
		// 원본 파일명과 저장될 파일 이름 설정
		String fileName = mr.getFilesystemName("ofile");
		if(fileName != null) {	// 첨부파일이 Uploads 폴더에 존재하는 경우 파일 이름을 수정해서 저장
			
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date()); // 날짜를 처리
			String ext = fileName.substring(fileName.lastIndexOf(".")); //.뒤에있는 확장자까지만 가지고 옴
			String newFileName = now + ext;
			
			System.out.println("now : " + now);
			System.out.println("ext : " + ext);
			System.out.println("newFileName : " + newFileName);
			
			// 파일명 변경
			File oldFile = new File (saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			
			oldFile.renameTo(newFile);
			
			// 변경한 내용 DTO에 저장
			dto.setOfile(fileName);	// 원본 파일 이름
			dto.setSfile(newFileName);	// 새로운 파일 이름 (서버에 저장될 파일 이름)
			
			//기존의 파일 삭제
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
			
		} else { // 첨부파일이 존재하지 않으면 기존의 이름 유지
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		// DB에 수정 내용을 반영 (DTO의 저장된 값을 DAO의 메서드에 매개변수로 전달)
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto); //result == 1: update 성공, result == 0: update 실패
		dao.close();
		
		// 수정 성공 vs 실패
		if(result == 1) { // 성공
			session.removeAttribute("pass");
			resp.sendRedirect("../mvc_board/view.do?idx=" + idx );
			
		}else {
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 해주세요", "../mvc_board/view.do?idx=" + idx);
		}
		
	}
	
}
