
public class Milk extends CondimentDecorator {

	public Milk(Beverage beverage) {
		setDecoratee(beverage);
	}
	
	@Override
	public String getDescription() {
		return getBeverage().getDescription() + ", Milk";
	}
	
	@Override
	public int cost() {
		return getBeverage().cost() + 100;
	}
	
}
