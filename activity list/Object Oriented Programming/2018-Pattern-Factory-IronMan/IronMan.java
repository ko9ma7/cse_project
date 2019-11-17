
public class IronMan {

	public static void main(String[] args) {
		
		TypeSuitFactory typeSuitFactory = new TypeSuitFactory();
		
		// new 키워드를 사용하여 인스턴스를 생성하지 않음
		Suit suit1 = typeSuitFactory.createSuit("stealth");
		Suit suit2 = typeSuitFactory.createSuit("space");
		Suit suit3 = typeSuitFactory.createSuit("");
		
		System.out.println(suit1.getName());
		System.out.println(suit2.getName());
		System.out.println(suit3.getName());

	}

}
