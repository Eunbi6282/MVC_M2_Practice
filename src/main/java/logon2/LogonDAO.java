package logon2;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{  //DAO 실제 데이터를 select, insert, delete
	
	// 싱글톤 패턴 : 외부에서 여러개의 객체를 생성하지 못하고 하나의 객체만 공유해서 사용하도록 함
		// 0. 객체 생성자는 private
		// 1. private static으로 객체 생성
	 	// 2. 생성된 객체를 method로 객체 전달 (public static)
	
	private static LogonDAO instance = new LogonDAO();
	
	// LogonDTO 객체를 리턴하는 메서드
		// 메서드를 사용해서만 객체를 생성할 수 있다.
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// 기본 생성자 : private -> 외부에서 객체 생성 불가능
		// 부모의 기본 생성자 호출
	private LogonDAO () {
		super();
	}
	
	// 회원가입 처리 (registerForm -> registerPro.jsp에서 넘어오는 값을 DB에 저장)
	public void insertMember (LogonDTO member) {  // dto인풋받기
		
		System.out.println ( "DTO birth 변수 값 : " + member.getU_birthday());
		
		try {
			// 폼에서 넘겨받은 password를 DB에 저장할 때 암호화
			String orgPass = member.getU_pass();
			String sql = "INSERT INTO member02 VALUES (?,?,?,?,?,?,?)";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getU_id());
			psmt.setString(2, member.getU_pass());
			psmt.setString(3, member.getU_Name());
			psmt.setDate(4, member.getR_date());
			psmt.setString(5, member.getU_address());
			psmt.setString(6, member.getU_tel());
			psmt.setString(7,member.getU_birthday());
			psmt.executeQuery();
			
			System.out.println("회원정보 DB입력 성공");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 DB입력시 예외발생");
		}
	}
	
	// 아이디 중복처리
	public int confirmId(String id) {
		int x = -1; 
			// x 가 -1 이면 같은 아이디가 DB에 존재하지 않음. x 가 1 이면 같은 아이디가 DB에 존재함
		try {
			String sql = "SELECT u_id FROM member02 WHERE u_id=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			System.out.println(sql);
			
			if(rs.next()) {	// 아이디가 dB에 존재하는 경우
				System.out.println("존재하는 아이디입니다. ID : " + id);
				x = 1;
			} else {
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이디 중복확인중 예외발생");
		} finally {
			//instance.close();
		}
		return x; 
	}
	
	// 로그인 처리 (loginPro.jsp) : 폼에서 넘겨받은 아이디와 패스워드를 DB와 대조
		// 사용자 인중 -> DB정보 수정, DB정보 삭제
		// 사용자 인증(MEmberCheck.jsp)에서 사용하는 메서드
	public int userCheck (String id, String passwd) {
		int x = -1; // x : -1 아이디 존재하지 않음. x : 1  아이디 존재(인증 성공)
		
		String orgPass = passwd;
		String sql = "SELECT u_pass FROM member02 WHERE u_id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // 아이디가 존재하면 rs.next가 true
				String dbpasswd = rs.getString("u_pass"); // db에서 가져온 패스워드를 dbpasswd에 담기
				
				if(orgPass.equals(dbpasswd)) {  
					x = 1;  // 폼에서 넘겨온 패스워드와 db에서 가져온 패스워드가 일치할 때 x에 1을 할당
				} else if (!orgPass.equals(dbpasswd)) {   // 같지 않다면 
					System.out.println(orgPass + "는 존재하지 않는 비밀번호입니다.");
					x = 0;  // 존재하지 않는 비밀번호
				} else {
					x = -1; // 존재하지 않는 아이디
					System.out.println(id + "는 존재하지 않는 아이디입니다. ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("인증실패");
		} finally {
			//instance.close();
		}
		return x;
	}
	
	// 
	
	
	

}