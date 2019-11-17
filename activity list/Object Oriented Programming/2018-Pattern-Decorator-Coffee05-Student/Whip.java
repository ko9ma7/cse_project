
public class Whip extends CondimentDecorator {
	
	public Whip(Beverage beverage){
		setDecoratee(beverage);
	}
	
	@Override
	public String getDescription() {
		return getBeverage().getDescription() + ", Whip";
	}
	
	@Override
	public int cost() {
		return getBeverage().cost()+500;
	}

}
