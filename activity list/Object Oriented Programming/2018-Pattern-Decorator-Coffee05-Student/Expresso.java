
public class Expresso extends Beverage {

	public Expresso() {
		setDescription("Expresso");
	}
	
	@Override
	public int cost() {
		return 4000;
	}

	@Override
	protected Beverage getBeverage() {
		// TODO Auto-generated method stub
		return null;
	}
}
