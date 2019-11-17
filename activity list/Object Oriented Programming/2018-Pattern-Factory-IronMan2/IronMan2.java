
public class IronMan2 {

	public static void main(String[] args) {
		
		Suit suit1 = SuitFactory.getSuit(new CombatFactory());
		Suit suit2 = SuitFactory.getSuit(new SpaceFactory());
		Suit suit3 = SuitFactory.getSuit(new HydroFactory());
		Suit suit4 = SuitFactory.getSuit(new StealthFactory());
		
		System.out.println(suit1.getName());
		System.out.println(suit2.getName());
		System.out.println(suit3.getName());
		System.out.println(suit4.getName());
		
	}

}
