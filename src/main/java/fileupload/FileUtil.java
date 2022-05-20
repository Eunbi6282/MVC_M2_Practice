package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// ���� ���ε� ó���ϴ� Ŭ���� 

public class FileUtil {
	// ���� ���ε�(multipart/form-data ��û) ó��
	// MultipartRequest : cos.jar ���̺귯���� ����, ���� ���ε� ó��
	
	public static MultipartRequest uploadFile(HttpServletRequest req, 
		String saveDirectory, int maxPostSize) {
	
		try {
			// ���ε� ����
			return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
			
			
		} catch (Exception e) {
			// ���ε� ����
			e.printStackTrace();
			System.out.println("���� ���ε� ����");
			return null;
		}
	}
	
	// �� �����ÿ� ���� ���ε�� ������ ���ϵ� �Բ� ����
	public static void deleteFile (HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator + filename);
			// ���ε� ���丮 + \ + filename
		
		if( file.exists()) {
			file.delete();
		}
	}
	
	// ���� �ٿ�ε� ó�� �޼��� : ����� ������ ã�� �ٿ�ε� �մϴ�.
	public static void download (HttpServletRequest req, HttpServletResponse resp, 
			String directory , String sfileName, String ofileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		
		try {
			// ������ ã�� �Է� ��Ʈ������
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			// �ѱ۱�������ó�� (���ϸ��� �ѱ��� ��)
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64") == -1) {
				ofileName = new String(ofileName.getBytes("UTF-8"),"ISO-8895");
			}else {
				ofileName = new String(ofileName.getBytes("KSC5601"),"ISO-8895-1");
			}
			
			// ���� �ٿ�� ���� ��� ����
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", 
					"attachment; filename\"" + ofileName + "\"");
			resp.setHeader("Content-Length", "" + file.length());
			
			//response ���� ��ü�κ��� ���ο� ��� ��Ʈ�� ����
			OutputStream oStream = resp.getOutputStream();
			
			// ��½�Ʈ���� ���� ���� ���
			byte b[] = new byte [(int)file.length()];
			int readBuffer = 0;
			while ((readBuffer = iStream.read(b)) > 0) {
				oStream.write(b, 0 ,readBuffer);
			}
			
			// ����� ��Ʈ�� ��ü ����.
			iStream.close();
			oStream.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
