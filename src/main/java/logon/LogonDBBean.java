package logon; 

import common.DBConnPool;
import work.crypt.BCrypt;
import work.crypt.SHA256; 

public class LogonDBBean extends DBConnPool{ // DAO : 실제 DB를 select, insert, delete, update

	// LogonDBBean 전역 객체 생성 <-- 한개의 객체만 생성해서 공유 (싱글톤 패턴)
		// 외부의 다른 클래스에서 new 키를 사용하면 여러개의 객체를 생성할 수 있다당
		// 특정 클래스는 여러개의 객체를 생성하지 못하도록 하고 단 하나의 객체만 생성해서 사용해야 할 경우 존재 
			// ex ) 회사 , 
	
		// 싱글톤 패턴 : 외부에서 여러개의 객체를 생성하지 못하고 하나의 객체만 공유해서 사용하도록 함. 
			// 0. 객체 생성자는 private으로 세팅을 먼저 해야 한다. 
			// 1. private static으로 객체 생성
			// 2. 생성된 객체를 method로 객체 전달 (public static)
	private static LogonDBBean instance = new LogonDBBean();	
	
	//LogonDBBean 객체를 리턴하는 메서드
		// 메서드를 사용해서만 객체를 생성할 수 있다.
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	// 기본생성자 : private => 외부에서 객체 생성 불가능
		// 부모 클래스의 기본 생성자 호출
	private LogonDBBean () { super(); };
	
	// 회원가입 처리 (regiserForm -> registerPro.jsp)에서 넘어오는 값을 DB에 저장 (Insert) 
	public void insertMember (LogonDataBean Member) { // dto인풋받기
//		SHA256 sha = SHA256.getInsatnce();
		
		try { 
			// 폼에서 넘겨받은 PassWord를 DB에 저장할 때 암호화
				// orgPass : Form에서 넘겨받은 Pass (orginPassWord)
				// // bcPass : 암호화 된 암호
			String orgPass = Member.getPasswd();
//			String shaPass = sha.Sha256Encrypt(orgPass.getBytes()); // hash
//			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
				// bcPass : 암호화 된 암호
			
			System.out.println("암호화되지 않은 패스워드 : " + orgPass);
//			System.out.println("암호화된 패스워드 : " + bcPass);
			String sql = "INSERT INTO member01 VALUES(?,?,?,?,?,?)";
			
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1,Member.getId());
//			psmt.setString(2, bcPass );   => 암호화된 password 저장
			psmt.setString(2, Member.getPasswd() );  //암호화 되지 않은 패스워드 저장
			psmt.setString(3, Member.getName());
			psmt.setTimestamp(4, Member.getReg_date());
			psmt.setString(5, Member.getAddress());
			psmt.setString(6, Member.getTel());
			psmt.executeUpdate();
			
			System.out.println("회원정보 DB입력 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 DB입력시 예외발생");
		}finally {
//			instance.close();
		}
	}
	
	// 로그인 처리 (loginPro.jsp) : 폼에서 넘겨받은 아이디와 패스워드를 DB를 확인.
		// 사용자 인증 , DB의 정보를 수정할 때, DB의 정보를 삭제할 때. 
		// 사용자 인증 (MemberCheck.jsp)에서 사용하는 메서드
	public int userCheck(String id, String passwd) {
		int x = -1;  // x = -1 : 아이디가 존재하지 않음 
					//  x = 1 : 인증성공, 
		
		// 복호화 : 암호화된 Password를 해독된 Password로 변환
//		SHA256 sha = SHA256.getInsatnce();
		
		try {
			String orgPass = passwd;
//			String shaPass = sha.getSha256(orgPass.getBytes());
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {	// 아이디가 존재하면 rs.next가 true
				String dbpasswd = rs.getString("passwd"); // db에서 가져온 패스워드를 dbpasswd에 담기
				
//				if(BCrypt.checkpw(shaPass, dbpasswd)) {
				if(orgPass.equals(dbpasswd)) {
					x = 1;  // 폼에서 넘겨온 패스워드와 DB에서 가져온 패스워드가 일치할 때 x에 1을 할당
				} else if(!orgPass.equals(dbpasswd)) {
					System.out.println(orgPass + "는 존재하지 않는 비밀번호 입니다.");
					x = 0;
				} else {
					System.out.println(id + " 는 존재하지 않는 아이디입니다.");
					x = -1;  // 패스워드가 일치하지 않을 때
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("인증실패했습니다. ");
		}finally {
//			instance.close(); // 객체 사용 종료. rs, psmt, con
		}
		return x;
	}
	
	// 아이디 중복 체크 (confirmId.jsp) : 아이디 중복을 확인하는 메서드
	public int confirmId (String id) {
		int x = -1; 	// x = -1 : 같은 아이디가 DB에 존재하지 않음 
						//  x = 1 : 같은 아이디가 DB에 존재 (중복)
		
		try {
			String sql = "SELECT id FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // 아이디가 DB에 존재하는 경우
				
				System.out.println("존재하는 ID입니다. 아이디: " + id);
				
				x = 1;
			} else { // 아이디가 DB에 존재하지 않는 경우
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이디 중복 체크 중 예외발생");
		}
		return x;
	}
	
	// 회원정보 수정 (modifyForm.jsp): DB에서 회원정보의 값을 가져오는 메서드
	public LogonDataBean getMember (String id, String passwd) {
		// DTO 리턴 시켜주기
		LogonDataBean member = null;
//		SHA256 sha = SHA256.getInsatnce();
		
		try {
			String orgPass = passwd;
//			String shaPass = sha.getSha256(orgPass.getBytes()); // orgPass를 암호화 시키기
			
			String sql = "SELECT * FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {	// 해당 아이디가 DB에 존재한다.
				String dbpasswd = rs.getString("passwd"); // db의 패스워드를 변수에 담는다.  
				
//				if( BCrypt.checkpw(shaPass, dbpasswd)) {
				if(orgPass.equals(dbpasswd)) {
					// DB의 passwd와 폼에서 넘겨온 Pass가 같을 때 처리할 부분
						// DB에서 select한 레코드를 DTO (LogonDataBean)에 Setter주입해서 값을 반환
					
					// 객체 생성후 DB의 rs에 저장된 값을 Setter주입
					member = new LogonDataBean();
					
					member.setId(rs.getString("id"));  // 컬럼명 , rs.getString(1)도 가능
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}else {
					// DB의 passwd와 폼에서 넘겨온 Pass가 다를 때 처리할 부분
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 정보 읽어오는 중 예외 발생");
		}finally {
//			instance.close(); // 객체 사용 종료. rs, psmt, con
		}
		return member; // member (LogonDataBean) : DTO에 Setter주입후 객체 리턴해줌
	}
	
	// 수정 페이지에서 수정한 내용을 DB에 저장하는 메서드 
		// 회원정보 수정처리 (modifyPro.jsp) 에서 회원정보 수정을 처리하는 메서드
	public int updateMember (LogonDataBean member) {
		int x = -1 ; // 업데이트 실패
		//LogonDataBean member = new LogonDataBean();
		// 아이디와 패스워드 확인 절차를 거친 후 업데이트 진행
			// 넘어온 비밀번호를 암호화 시켜서 db에 있는 암호화된 비밀번호의 값과 대조해야 함
//		SHA256 sha = SHA256.getInsatnce();	// 객체 활성화
		try {
			String orgPass = member.getPasswd();   // 비밀전호 안들어올 경우 유효성검사
//			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId()); 
			rs= psmt.executeQuery(); 
			
			if (rs.next()) { // 해당 아이디가 db에 존재한다.
				// 폼에서 넘긴 패스워드와 DB에서 가져온 패스워드가 일치하는지 확인 후 처리
				String dbpasswd = rs.getString("passwd");
				
//				if(BCrypt.checkpw(shaPass, dbpasswd)) { // 두 패스워드가 일치할 때
				if(orgPass.equals(dbpasswd)) {
					// DTO(member) 에서 들어온 값을 db에 UPDATE
					sql = "UPDATE member01 SET name = ?, address = ?, tel = ?"
							+ " WHERE id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, member.getName());
					psmt.setString(2, member.getAddress());
					psmt.setString(3, member.getTel());
					psmt.setString(4, member.getId());
					
					psmt.executeUpdate();
					x = 1; //update성공시 x 변수에 1을 할당
					
				}
			}else { // 해당 아이디가 존재하지 않는다. 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원정보 수정 시 예외발생");
		}finally {
//			instance.close(); // 객체 사용 종료. rs, psmt, con
		}
		return x;
	}
	
	// 회원 탈퇴 처리 (deletePro.jsp) 에서 회원정보 삭제 메서드
	public int deleteMember(String id, String passwd) {
		int x = -1;  // x = -1:회원 탈퇴 실패
					// x = 1; 회원 탈퇴 성공
		
//		SHA256 sha = SHA256.getInsatnce(); // 객체를 호출해서 변수에 할당
		try {
			String orgPass = passwd;
//			String shaPass = sha.getSha256(orgPass.getBytes());
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs= psmt.executeQuery();
			
			if (rs.next()) { // 아이디가 DB에 존재하는 경우
				String dbpasswd = rs.getString("passwd");
//				if (BCrypt.checkpw(shaPass, dbpasswd)) {
				if(orgPass.equals(dbpasswd)) {
					sql = "DELETE member01 WHERE id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.executeUpdate(); // 업데이트 실패하면 exeception 발생할거임
					x = 1;  // delte성공시 
				}else {
					x= 0;  // ID는 존재하고 passwd가 일치하지 않을 때
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원 탈퇴 시 예외발생");
		} finally {
//			instance.close(); // 객체 사용 종료. rs, psmt, con
		}
		return x;  // 성공시 x=1, 실패시 x=-1
	};
	
	
		
	
	
	
	
	
	
	
	
	
}
