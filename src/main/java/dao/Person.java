package dao;

public class Person {
	private int id = 20181004;		// private : useBean getProperty, setProperty
										// 실제는 getter를 통해서 접근, setter를 통해서 접근
	private String name = "홍길동";	// private : useBean getProperty, setProperty
	
	public Person() {} // 기본 생성자 : 실행블럭 비어있음
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}; 
	
}
