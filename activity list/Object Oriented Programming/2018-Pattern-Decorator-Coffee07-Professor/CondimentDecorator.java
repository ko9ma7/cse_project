
public abstract class CondimentDecorator extends Beverage {
	public abstract String getDescription();
	public boolean doubleAllowed() {
		return true;
	}
}
