// IronMan 클래스 참조
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage; // 장식할 beverage
	protected CondimentDecorator(Beverage beverage) {
		this.beverage = beverage;
	}
	public abstract String getDescription();
}
