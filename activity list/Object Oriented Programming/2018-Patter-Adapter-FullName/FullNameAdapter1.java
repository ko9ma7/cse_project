// 객체 어댑터 방식
public class FullNameAdapter1 implements FullName { // HAS-A

	User user;
	
	public FullNameAdapter1(User user) {
		this.user = user;
	}
	
	@Override
	public String getFullName() {
		return user.getFirstName() + user.getLastName();
	}

}
