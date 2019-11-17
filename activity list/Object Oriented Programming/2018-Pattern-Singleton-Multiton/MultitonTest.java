
public class MultitonTest {

	public static void main(String[] args) {
		Multiton instance1 = Multiton.getInstance("first");
		Multiton instance2 = Multiton.getInstance("second");
		Multiton instance3 = Multiton.getInstance("third");
		Multiton instance4 = Multiton.getInstance("first");
		
		if (instance1 == instance4) System.out.println("올바르게 동작");
		Multiton instance5 = Multiton.getInstance("fourth"); // 제한된 개수 이상의 객체 생성 요청
	}

}
