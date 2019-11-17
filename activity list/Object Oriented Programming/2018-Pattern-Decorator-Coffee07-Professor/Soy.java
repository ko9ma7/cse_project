
public class Soy extends CondimentDecorator {
	private Beverage beverage;
	protected Soy(Beverage beverage){
		this.beverage = beverage;
		
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", Soy";
	}
	@Override
	public int cost() {
		return beverage.cost()+400;
	}
	public boolean doubleAllowed() {
		return false;
	}
}
