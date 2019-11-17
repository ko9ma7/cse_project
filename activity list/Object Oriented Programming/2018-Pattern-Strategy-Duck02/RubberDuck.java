
public class RubberDuck extends Duck {
	public RubberDuck(FlyingStrategy flyingStrategy) {
		super(flyingStrategy);
	}
	@Override
	public void quack() {
		System.out.println("삑삑");
	}
	@Override
	public void display() {
		System.out.println("난 고무오리");
	}

}
