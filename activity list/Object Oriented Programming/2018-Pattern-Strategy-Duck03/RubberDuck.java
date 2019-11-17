
public class RubberDuck extends Duck {
	
	public RubberDuck(FlyingStrategy flyingStrategy, QuackableStrategy quackStrategy) {
		
		this.flyStrategy = flyingStrategy;
		this.quackStrategy = quackStrategy;
		
	}
	
	@Override
	public void display() {
		System.out.println("난 고무오리");
	}

}
