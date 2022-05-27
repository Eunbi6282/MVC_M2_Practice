package membership;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	public MemberDAO() {
		super();
	}
	
	// Ŭ���̾�Ʈ�� ID�� Password���� �޾ƿͼ� ȸ�� ���̺��� ������ ��ġ�ϴ� ȸ�� ������ DTO�� ��Ƽ� ��ȯ
	public MemberDTO getMemberDTO (String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id = ? AND pass = ?";
			// 1���� ���ڵ尡 ��µǸ� :id�� pass�� �����ϴ� ����
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			
			// rs�� ���� dto�� ����
			if (rs.next()) { // ���ڵ��� ���� �����ϸ�, rs.next()
				dto.setId(rs.getString("id")); // db�� �÷��̸�
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));  // index 3��
				dto.setRegidate(rs.getString(4));
				
				System.out.println("���� ����");
			} else {
				System.out.println("���� ����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���̵� �н����� Ȯ�ν� ���ܹ߻�");
		}
		
		
		return dto; //Client���� uid, upass���� �޾ƿͼ� DB���� �˻� �� �˻��� ���� DTO�� ��Ƽ� ���� ��ȯ
	}

	
	
	
	
	
	
	
	
	
	
}
