
public class Decaf extends Beverage {
	
	public Decaf() {
		setDescription("Decaf");
	}
	
	@Override
	public int cost() {
		return 3000;
	}

	@Override
	protected Beverage getBeverage() {
		// TODO Auto-generated method stub
		return null;
	}

}
