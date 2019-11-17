
public class Mocha extends CondimentDecorator {
	public Mocha(Beverage beverage){
		super(beverage);
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+", Mocha";
	}
	@Override
	public int cost() {
		return beverage.cost()+200;
	}
	@Override
	public Beverage removeCondiment() {
		
//		System.out.println(this.getClass()); // 장식한 이후의 beverage의 클래스
//		System.out.println(this.beverage.getClass()); // 장식하기 전 이미 생성된 beverage의 클래스
		
		if (this.getClass() == beverage.getClass()) {
			return this;
		} else {
			return this.beverage;
		}
		
	}
}
