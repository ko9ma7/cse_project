
public class Whip extends CondimentDecorator {
	public Whip(Beverage beverage){
		super(beverage);
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
