
public class Soy extends CondimentDecorator {
	public Soy(Beverage beverage){
		super(beverage);		
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", Soy";
	}
	@Override
	public int cost() {
		return beverage.cost()+400;
	}
	@Override
	protected boolean doubleAllowed() {
		return false;
	}
}
