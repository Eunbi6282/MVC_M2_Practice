package logon2;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import common.DBConnPool;

public class LogonDAO extends DBConnPool{  //DAO ���� �����͸� select, insert, delete
	
	// �̱��� ���� : �ܺο��� �������� ��ü�� �������� ���ϰ� �ϳ��� ��ü�� �����ؼ� ����ϵ��� ��
		// 0. ��ü �����ڴ� private
		// 1. private static���� ��ü ����
	 	// 2. ������ ��ü�� method�� ��ü ���� (public static)
	
	private static LogonDAO instance = new LogonDAO();
	
	// LogonDTO ��ü�� �����ϴ� �޼���
		// �޼��带 ����ؼ��� ��ü�� ������ �� �ִ�.
	public static LogonDAO getInstance() {
		return instance;
	}
	
	// �⺻ ������ : private -> �ܺο��� ��ü ���� �Ұ���
		// �θ��� �⺻ ������ ȣ��
	private LogonDAO () {
		super();
	}
	
	// ȸ������ ó�� (registerForm -> registerPro.jsp���� �Ѿ���� ���� DB�� ����)
	public void insertMember (LogonDTO member) {  // dto��ǲ�ޱ�
		
		System.out.println ( "DTO birth ���� �� : " + member.getU_birthday());
		
		try {
			// ������ �Ѱܹ��� password�� DB�� ������ �� ��ȣȭ
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
			
			System.out.println("ȸ������ DB�Է� ����");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ȸ������ DB�Է½� ���ܹ߻�");
		}
	}
	
	// ���̵� �ߺ�ó��
	public int confirmId(String id) {
		int x = -1; 
			// x �� -1 �̸� ���� ���̵� DB�� �������� ����. x �� 1 �̸� ���� ���̵� DB�� ������
		try {
			String sql = "SELECT u_id FROM member02 WHERE u_id=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			System.out.println(sql);
			
			if(rs.next()) {	// ���̵� dB�� �����ϴ� ���
				System.out.println("�����ϴ� ���̵��Դϴ�. ID : " + id);
				x = 1;
			} else {
				x = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���̵� �ߺ�Ȯ���� ���ܹ߻�");
		} finally {
			//instance.close();
		}
		return x; 
	}
	
	// �α��� ó�� (loginPro.jsp) : ������ �Ѱܹ��� ���̵�� �н����带 DB�� ����
		// ����� ���� -> DB���� ����, DB���� ����
		// ����� ����(MEmberCheck.jsp)���� ����ϴ� �޼���
	public int userCheck (String id, String passwd) {
		int x = -1; // x : -1 ���̵� �������� ����. x : 1  ���̵� ����(���� ����)
		
		String orgPass = passwd;
		String sql = "SELECT u_pass FROM member02 WHERE u_id = ?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if( rs.next()) {  // ���̵� �����ϸ� rs.next�� true
				String dbpasswd = rs.getString("u_pass"); // db���� ������ �н����带 dbpasswd�� ���
				
				if(orgPass.equals(dbpasswd)) {  
					x = 1;  // ������ �Ѱܿ� �н������ db���� ������ �н����尡 ��ġ�� �� x�� 1�� �Ҵ�
				} else if (!orgPass.equals(dbpasswd)) {   // ���� �ʴٸ� 
					System.out.println(orgPass + "�� �������� �ʴ� ��й�ȣ�Դϴ�.");
					x = 0;  // �������� �ʴ� ��й�ȣ
				} else {
					x = -1; // �������� �ʴ� ���̵�
					System.out.println(id + "�� �������� �ʴ� ���̵��Դϴ�. ");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��������");
		} finally {
			//instance.close();
		}
		return x;
	}
	
	// 
	
	
	

}