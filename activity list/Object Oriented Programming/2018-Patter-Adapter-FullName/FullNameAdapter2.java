// 클래스 어댑터 방식
public class FullNameAdapter2 extends User implements FullName { // IS-A

	public FullNameAdapter2(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public String getFullName() {
		return this.getFirstName() + this.getLastName();
	}

}
