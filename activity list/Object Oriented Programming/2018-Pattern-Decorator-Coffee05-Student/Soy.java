
public class Soy extends CondimentDecorator {

	public Soy(Beverage beverage) {
		setDecoratee(beverage);
	}
	
	@Override
	public String getDescription() {
		return getBeverage().getDescription() + ", Soy";
	}
	
	@Override
	public int cost() {
		return getBeverage().cost() + 300;
	}
	
}
