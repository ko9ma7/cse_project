/**
 * 2018년도 2학기 객체지향개발론및실습
 * 전략 패턴
 * MallardDuck
 * @author sangjin
 *
 */
public class MallardDuck extends Duck {
	
	public MallardDuck(FlyingStrategy flyingStrategy, QuackableStrategy quackStrategy) {
		
		this.flyStrategy = flyingStrategy;
		this.quackStrategy = quackStrategy;
	}
	@Override
	public void display() {
		System.out.println("난 청둥오리");
	}
	
}
