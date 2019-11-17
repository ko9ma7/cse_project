
public class DarkRoast extends Beverage {

	public DarkRoast() {
		setDescription("DarkRoast");
	}
	
	@Override
	public int cost() {
		return 2000;
	}

	@Override
	protected Beverage getBeverage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
