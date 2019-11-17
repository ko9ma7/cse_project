
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	protected CondimentDecorator(Beverage beverage){
		if(!this.doubleAllowed()) {
			Beverage currentDecorator = beverage.getNext();
			while(currentDecorator!=null) {
				if(this.getClass()==currentDecorator.getClass())
					throw new IllegalArgumentException("Double "+this.getClass().getName()+" not allowed");
				currentDecorator = beverage.getNext();
			}
		}
		this.beverage = beverage;
	}
	public abstract String getDescription();
	@Override
	protected Beverage getNext() {
		return beverage;
	}
	@Override
	protected boolean doubleAllowed() {
		return true;
	}
}
