
public class Mocha extends CondimentDecorator {
	private Beverage beverage; // has-a
	public Mocha(Beverage beverage){
		this.beverage = beverage;
	}
	@Override
	public String getDescription() { // is-a
		return beverage.getDescription()+", Mocha";
	}
	@Override
	public int cost() {
		return beverage.cost()+200;
	}
}
