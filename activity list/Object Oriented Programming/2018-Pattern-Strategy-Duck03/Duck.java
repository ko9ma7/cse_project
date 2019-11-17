/**
 * 2018년도 2학기 객체지향개발론및실습
 * 전략 패턴
 * Duck
 * 추상클래스
 * @author sangjin
 *
 */
public abstract class Duck {
	protected FlyingStrategy flyStrategy; // Fly 인터페이스 추가
	protected QuackableStrategy quackStrategy; // Quak 인터페이스 추가
	public Duck() { }
	public void performFly() {
		flyStrategy.doFly();
	}
	public void performQuack() {
		quackStrategy.doQuack();
	}
	public void setFlyingStrategy(FlyingStrategy flyStrategy) {
		this.flyStrategy = flyStrategy;
	}
	public void setQuackableStrategy(QuackableStrategy quackStrategy) {
		this.quackStrategy = quackStrategy;
	}
	public void fly() {
		flyStrategy.doFly();
	}
	public void quack() {
		quackStrategy.doQuack();
	}
	public void swim() {
		System.out.println("나 물에서 수영하고 있어");
	}
	public abstract void display();
}
