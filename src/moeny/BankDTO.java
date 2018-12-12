package moeny;

public class BankDTO {

	private String id;//회원 아이디
	private String name;//회원 이름
	private int age;//회원 나이
	private String tel;//회원 전화번호
	
	
	
	public BankDTO() {//기본생성자
		super();
	}

	public BankDTO(String id, String name, int age, String tel) {//회원정보를 가져올수 있게 도와주는 생성자
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.tel = tel;
	}
	
	@Override
	public String toString() {//회원 전체 목록을 한번에 보여주기위한 메서드
		return "[id=" + id + ", name=" + name + ", age=" + age + ", tel=" + tel + "]\n";
	}

	//회원에 대한 정보를 가져오거나 세팅할수있게 해주는 get ,set 메서드
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
}//클래스끝 
