
public class NameTest {

	public static void main(String[] args) {
		
		User user = new User("지", "명화");
		FullName name;
		
		FullNameAdapter1 fullName1 = new FullNameAdapter1(user);
		FullNameAdapter2 fullName2 = new FullNameAdapter2(user.getFirstName(), user.getLastName());
		
		System.out.println(fullName1.getFullName());
		System.out.println(fullName2.getFullName());
	}

}
