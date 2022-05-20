package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 파일 업로드 처리하는 클래스 

public class FileUtil {
	// 파일 업로드(multipart/form-data 요청) 처리
	// MultipartRequest : cos.jar 라이브러리에 존재, 파일 업로드 처리
	
	public static MultipartRequest uploadFile(HttpServletRequest req, 
		String saveDirectory, int maxPostSize) {
	
		try {
			// 업로드 성공
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
			
			
		} catch (Exception e) {
			// 업로드 실패
			e.printStackTrace();
			System.out.println("파일 업로드 실패");
			return null;
		}
	}
	
	// 글 삭제시에 파일 업로드된 폴더의 파일도 함께 삭제
	public static void deleteFile (HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
			// 업로드 디렉토리 + \ + filename
		
		if( file.exists()) {
			file.delete();
		}
	}
	
	// 파일 다운로드 처리 메서드 : 명시한 파일을 찾아 다운로드 합니다.
	public static void download (HttpServletRequest req, HttpServletResponse resp, 
			String directory , String sfileName, String ofileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		
		try {
			// 파일을 찾아 입력 스트림생성
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			// 한글깨짐방지처리 (파일명이 한글일 때)
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1) {
				ofileName = new String(ofileName.getBytes("UTF-8"),"ISO-8895");
			}else {
				ofileName = new String(ofileName.getBytes("KSC5601"),"ISO-8895-1");
			}
			
			// 파일 다운용 응답 헤더 설정
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", 
					"attachment; filename\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length());
			
			//response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream oStream = resp.getOutputStream();
			
			// 출력스트림에 파일 내용 출력
			byte b[] = new byte [(int)file.length()];
			int readBuffer = 0;
			while ((readBuffer = iStream.read(b)) > 0) {
				oStream.write(b, 0 ,readBuffer);
			}
			
			// 입출력 스트림 객체 닫음.
			iStream.close();
			oStream.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
