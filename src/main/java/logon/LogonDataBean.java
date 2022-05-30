package logon;

import java.sql.Timestamp;

public class LogonDataBean {  // DTO, VO, Bean (DTO, DAO) : 백엔드에서 처리하는 java페이지
	private String id;
	private String passwd;
	private String name;
	private Timestamp reg_date; // 가입날짜의 시간을 등록
	private String address;
	private String tel;
	
		// 데이터를 Setter로 주입받아서 Getter로 출력해주는 전달자 역할
	
	//getter setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
}
