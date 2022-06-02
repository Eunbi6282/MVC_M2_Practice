package logon; 

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import common.DBConnPool;

public class LogonDBBean extends DBConnPool{ // DAO : ���� DB�� select, insert, delete, update

	// LogonDBBean ���� ��ü ���� <-- �Ѱ��� ��ü�� �����ؼ� ���� (�̱��� ����)
		// �ܺ��� �ٸ� Ŭ�������� new Ű�� ����ϸ� �������� ��ü�� ������ �� �ִٴ�
		// Ư�� Ŭ������ �������� ��ü�� �������� ���ϵ��� �ϰ� �� �ϳ��� ��ü�� �����ؼ� ����ؾ� �� ��� ���� 
			// ex ) ȸ�� , 
	
		// �̱��� ���� : �ܺο��� �������� ��ü�� �������� ���ϰ� �ϳ��� ��ü�� �����ؼ� ����ϵ��� ��. 
			// 0. ��ü �����ڴ� private���� ������ ���� �ؾ� �Ѵ�. 
			// 1. private static���� ��ü ����
			// 2. ������ ��ü�� method�� ��ü ���� (public static)
	private static LogonDBBean instance = new LogonDBBean();	
	
	//LogonDBBean ��ü�� �����ϴ� �޼���
		// �޼��带 ����ؼ��� ��ü�� ������ �� �ִ�.
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	// �⺻������ : private => �ܺο��� ��ü ���� �Ұ���
		// �θ� Ŭ������ �⺻ ������ ȣ��
	private LogonDBBean () { super(); };
	
	// ȸ������ ó�� (regiserForm -> registerPro.jsp)���� �Ѿ���� ���� DB�� ���� (Insert) 
	public void insertMember (LogonDataBean Member) { // dto��ǲ�ޱ�
//		SHA256 sha = SHA256.getInsatnce();
		
		try { 
			// ������ �Ѱܹ��� PassWord�� DB�� ������ �� ��ȣȭ
				// orgPass : Form���� �Ѱܹ��� Pass (orginPassWord)
				// // bcPass : ��ȣȭ �� ��ȣ
			String orgPass = Member.getPasswd();
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 ���ڵ�
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64���ڵ�
			Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(encodedBytes);
			String decodedTxt = new String (decodedBytes);
				// bcPass : ��ȣȭ �� ��ȣ
			
			System.out.println("���ڵ��� orgPass : " + orgPass);
			System.out.println("���ڵ� : " +  encodedTxt);
			System.out.println("���ڵ� : " +  decodedTxt);
			
			
			System.out.println("��ȣȭ���� ���� �н����� : " + orgPass);
//			System.out.println("��ȣȭ�� �н����� : " + bcPass);
			String sql = "INSERT INTO member01 VALUES(?,?,?,?,?,?)";
			
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1,Member.getId());
			psmt.setString(2, encodedTxt );   //=> ��ȣȭ�� password ����
//			psmt.setString(2, Member.getPasswd() );  //��ȣȭ ���� ���� �н����� ����
			psmt.setString(3, Member.getName());
			psmt.setTimestamp(4, Member.getReg_date());
			psmt.setString(5, Member.getAddress());
			psmt.setString(6, Member.getTel());
			psmt.executeUpdate();
			
			System.out.println("ȸ������ DB�Է� ����");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ������ DB�Է½� ���ܹ߻�");
		}finally {
//			instance.close();
		}
	}
	
	// �α��� ó�� (loginPro.jsp) : ������ �Ѱܹ��� ���̵�� �н����带 DB�� Ȯ��.
		// ����� ���� , DB�� ������ ������ ��, DB�� ������ ������ ��. 
		// ����� ���� (MemberCheck.jsp)���� ����ϴ� �޼���
	public int userCheck(String id, String passwd) {
		int x = -1;  // x = -1 : ���̵� �������� ���� 
					//  x = 1 : ��������, 
		
		// ��ȣȭ : ��ȣȭ�� Password�� �ص��� Password�� ��ȯ
		
		try {
			String orgPass = passwd;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 ���ڵ�
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64���ڵ�
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {	// ���̵� �����ϸ� rs.next�� true
				String dbpasswd = rs.getString("passwd"); // db���� ������ �н����带 dbpasswd�� ���
					// ���ڵ� �Ǿ��ֱ� ������ ���ڵ��ؼ� �־�� ��
				
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeó����
				
				if(orgPass.equals(decodedTxt)) {
					x = 1;  // ������ �Ѱܿ� �н������ DB���� ������ �н����尡 ��ġ�� �� x�� 1�� �Ҵ�
				} else if(!orgPass.equals(decodedTxt)) {
					System.out.println(orgPass + "�� �������� �ʴ� ��й�ȣ �Դϴ�.");
					x = 0;
				} else {
					System.out.println(id + " �� �������� �ʴ� ���̵��Դϴ�.");
					x = -1;  // �н����尡 ��ġ���� ���� ��
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���������߽��ϴ�. ");
		}finally {
//			instance.close(); // ��ü ��� ����. rs, psmt, con
		}
		return x;
	}
	
	// ���̵� �ߺ� üũ (confirmId.jsp) : ���̵� �ߺ��� Ȯ���ϴ� �޼���
	public int confirmId (String id) {
		int x = -1; 	// x = -1 : ���� ���̵� DB�� �������� ���� 
						//  x = 1 : ���� ���̵� DB�� ���� (�ߺ�)
		
		try {
			String sql = "SELECT id FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // ���̵� DB�� �����ϴ� ���
				
				System.out.println("�����ϴ� ID�Դϴ�. ���̵�: " + id);
				
				x = 1;
			} else { // ���̵� DB�� �������� �ʴ� ���
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���̵� �ߺ� üũ �� ���ܹ߻�");
		}
		return x;
	}
	
	// ȸ������ DB���� �����ͼ� ���� (modifyForm.jsp): DB���� ȸ�������� ���� �������� �޼���
	public LogonDataBean getMember (String id, String passwd) {
		// DTO ���� �����ֱ�
		LogonDataBean member = null;
		
		try {
			String orgPass = passwd;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 ���ڵ�
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64���ڵ�
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT * FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {	// �ش� ���̵� DB�� �����Ѵ�.
				String dbpasswd = rs.getString("passwd"); // db�� �н����带 ������ ��´�.  
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeó����
				
				if(orgPass.equals(decodedTxt)) {
					// DB�� passwd�� ������ �Ѱܿ� Pass�� ���� �� ó���� �κ�
						// DB���� select�� ���ڵ带 DTO (LogonDataBean)�� Setter�����ؼ� ���� ��ȯ
					
					// ��ü ������ DB�� rs�� ����� ���� Setter����
					member = new LogonDataBean();
					
					member.setId(rs.getString("id"));  // �÷��� , rs.getString(1)�� ����
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}else {
					// DB�� passwd�� ������ �Ѱܿ� Pass�� �ٸ� �� ó���� �κ�
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� ���� �о���� �� ���� �߻�");
		}finally {
//			instance.close(); // ��ü ��� ����. rs, psmt, con
		}
		return member; // member (LogonDataBean) : DTO�� Setter������ ��ü ��������
	}
	
	// ���� ���������� ������ ������ DB�� �����ϴ� �޼��� 
		// ȸ������ ����ó�� (modifyPro.jsp) ���� ȸ������ ������ ó���ϴ� �޼���
	public int updateMember (LogonDataBean member) {
		int x = -1 ; // ������Ʈ ����
		//LogonDataBean member = new LogonDataBean();
		// ���̵�� �н����� Ȯ�� ������ ��ģ �� ������Ʈ ����
			// �Ѿ�� ��й�ȣ�� ��ȣȭ ���Ѽ� db�� �ִ� ��ȣȭ�� ��й�ȣ�� ���� �����ؾ� ��
		try {
			String orgPass = member.getPasswd();   // �����ȣ �ȵ��� ��� ��ȿ���˻�
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 ���ڵ�
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64���ڵ�
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getId()); 
			rs= psmt.executeQuery(); 
			
			if (rs.next()) { // �ش� ���̵� db�� �����Ѵ�.
				// ������ �ѱ� �н������ DB���� ������ �н����尡 ��ġ�ϴ��� Ȯ�� �� ó��
				String dbpasswd = rs.getString("passwd");
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeó����
				
				if(orgPass.equals(decodedTxt)) {
					// DTO(member) ���� ���� ���� db�� UPDATE
					sql = "UPDATE member01 SET name = ?, address = ?, tel = ?"
							+ " WHERE id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, member.getName());
					psmt.setString(2, member.getAddress());
					psmt.setString(3, member.getTel());
					psmt.setString(4, member.getId());
					
					psmt.executeUpdate();
					x = 1; //update������ x ������ 1�� �Ҵ�
					
				}
			}else { // �ش� ���̵� �������� �ʴ´�. 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ������ ���� �� ���ܹ߻�");
		}finally {
//			instance.close(); // ��ü ��� ����. rs, psmt, con
		}
		return x;
	}
	
	// ȸ�� Ż�� ó�� (deletePro.jsp) ���� ȸ������ ���� �޼���
	public int deleteMember(String id, String passwd) {
		int x = -1;  // x = -1:ȸ�� Ż�� ����
					// x = 1; ȸ�� Ż�� ����
		
		try {
			String orgPass = passwd;
			byte[] targetBytes = orgPass.getBytes();
			
			// Base64 ���ڵ�
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			String encodedTxt = new String(encodedBytes);
			
			// Base64���ڵ�
			Decoder decoder = Base64.getDecoder();
			
			String sql = "SELECT passwd FROM member01 WHERE id = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs= psmt.executeQuery();
			
			if (rs.next()) { // ���̵� DB�� �����ϴ� ���
				String dbpasswd = rs.getString("passwd");
				byte[] decodedBytes = decoder.decode(dbpasswd);
				String decodedTxt = new String (decodedBytes); // decodeó����
				
				if(orgPass.equals(decodedTxt)) {
					sql = "DELETE member01 WHERE id = ?";
					psmt = con.prepareStatement(sql);
					psmt.setString(1, id);
					psmt.executeUpdate(); // ������Ʈ �����ϸ� exeception �߻��Ұ���
					x = 1;  // delte������ 
				}else {
					x= 0;  // ID�� �����ϰ� passwd�� ��ġ���� ���� ��
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ�� Ż�� �� ���ܹ߻�");
		} finally {
//			instance.close(); // ��ü ��� ����. rs, psmt, con
		}
		return x;  // ������ x=1, ���н� x=-1
	};
	
	
		
	
	
	
	
	
	
	
	
	
}
