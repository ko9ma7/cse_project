
public class Whip extends CondimentDecorator {
	private Beverage beverage;
	public Whip(Beverage beverage){
		this.beverage = beverage;
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", Whip";
	}
	@Override
	public int cost() {
		return beverage.cost()+500;
	}
}
