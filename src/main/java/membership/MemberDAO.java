package membership;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	public MemberDAO() {
		super();
	}
	
	// 클라이언트의 ID와 Password값을 받아와서 회원 테이블의 정보와 일치하는 회원 정보를 DTO에 담아서 반환
	public MemberDTO getMemberDTO (String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id = ? AND pass = ?";
			// 1개의 레코드가 출력되면 :id와 pass가 존재하는 것임
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			// rs의 값을 dto에 저장
			if (rs.next()) { // 레코드의 값이 존재하면, rs.next()
				dto.setId(rs.getString("id")); // db의 컬럼이름
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));  // index 3번
				dto.setRegidate(rs.getString(4));
				
				System.out.println("인증 성공");
			} else {
				System.out.println("인증 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이디 패스워드 확인시 예외발생");
		}
		
		
		return dto; //Client에서 uid, upass값을 받아와서 DB에서 검색 후 검색된 값을 DTO에 담아서 값을 반환
	}

	
	
	
	
	
	
	
	
	
	
}
