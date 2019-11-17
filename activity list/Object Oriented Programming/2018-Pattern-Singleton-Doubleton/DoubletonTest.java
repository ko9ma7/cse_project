
public class DoubletonTest {

	public static void main(String[] args) {
			Doubleton first = Doubleton.getInstance();
			Doubleton second = Doubleton.getInstance();
			Doubleton third = Doubleton.getInstance();
			
			System.out.println(first);
			System.out.println(second);
			System.out.println(third);
	}

}
