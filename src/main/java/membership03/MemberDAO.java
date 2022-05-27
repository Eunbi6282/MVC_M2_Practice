package membership03;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	public MemberDAO() {
		super();
	}
	
	// Ŭ���̾�Ʈ�� ID�� Password���� �޾ƿͼ� ȸ�� ���̺��� ������ ��ġ�ϴ� ȸ�� ������ DTO�� ��Ƽ� ��ȯ
	public MemberDTO getMemberDTO (String uid, String upass) {
		MemberDTO dto = new MemberDTO(); // dto��ü�� return
		
		String query = "SELECT * FROM member03 WHERE id = ? AND pass = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs= psmt.executeQuery();
			
			// rs�� ���� dto�� ����
			if(rs.next()) { // ���ڵ忡 ���� �����ϸ�, rs.next()
				// db�� �����ض�
				dto.setId(rs.getString("id")); // �÷��̸�
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				dto.setGrade(rs.getString(5));
				
				System.out.println("���� ����");
			}else {
			System.out.println("���� ����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���̵� �н����� Ȯ�ν� ���� �߻�");
		}
		return dto;
	}
	
	public MemberDTO getGrade (String id) {
		MemberDTO dto = new MemberDTO(); // dto��ü�� return
		String query = "SELECT grade FROM member03 WHERE id = ?";
		
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getString("id")); // �÷��̸�
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getString(4));
				dto.setGrade(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("��� select �� ���ܹ߻�");
		}
		
		return dto;
		
	}
	
	
	
	
	
	
	
}
