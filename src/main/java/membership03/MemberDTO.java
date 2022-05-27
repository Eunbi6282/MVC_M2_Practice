package membership03;

public class MemberDTO {
	private String id;
	private String pass;
	private String name;
	private String regidate;
	private String grade;
	
	public MemberDTO() {
		System.out.println("MemberDTO가 잘 생성되었습니다.");
	}
	
	//Getter Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegidate() {
		return regidate;
	}
	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	

	
	
}
