package day1;

public class Member {
	private int idx;
	private String email;
	private String password;
	
	public Member(int idx, String email, String password) {
		super();
		this.idx = idx;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
}
