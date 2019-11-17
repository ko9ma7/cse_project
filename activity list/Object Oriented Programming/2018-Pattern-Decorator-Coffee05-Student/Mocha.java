
public class Mocha extends CondimentDecorator {
	
	public Mocha(Beverage beverage){
		setDecoratee(beverage);
	}
	
	@Override
	public String getDescription() { // CondimentDecorator클래스에서 추상 메소드였으므로 반드시 구현
		return getBeverage().getDescription() + ", Mocha";
	}
	
	@Override
	public int cost() {
		return getBeverage().cost()+200;
	}
	
}
