package membership03;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	public MemberDAO() {
		super();
	}
	
	// 클라이언트의 ID와 Password값을 받아와서 회원 테이블의 정보와 일치하는 회원 정보를 DTO에 담아서 반환
	public MemberDTO getMemberDTO (String uid, String upass) {
		MemberDTO dto = new MemberDTO(); // dto객체를 return
		
		String query = "SELECT * FROM member03 WHERE id = ? AND pass = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs= psmt.executeQuery();
			
			// rs의 값을 dto에 저장
			if(rs.next()) { // 레코드에 값이 존재하면, rs.next()
				// db에 저장해라
				dto.setId(rs.getString("id")); // 컬럼이름
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				dto.setGrade(rs.getString(5));
				
				System.out.println("인증 성공");
			}else {
			System.out.println("인증 실패");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("아이디 패스워드 확인시 예외 발생");
		}
		return dto;
	}
	
	public MemberDTO getGrade (String id) {
		MemberDTO dto = new MemberDTO(); // dto객체를 return
		String query = "SELECT grade FROM member03 WHERE id = ?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id")); // 컬럼이름
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				dto.setGrade(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("등급 select 중 예외발생");
		}
		
		return dto;
		
	}
	
	
	
	
	
	
	
}
