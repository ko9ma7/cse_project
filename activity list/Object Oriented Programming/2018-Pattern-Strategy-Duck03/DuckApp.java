import java.util.ArrayList;
/**
 * 2018년도 2학기 객체지향개발론및실습
 * 전략 패턴
 * DuckApp
 * 오리응용 테스트 프로그램
 * @author sangjin
 *
 */
public class DuckApp {
	public static void flySimulation(Duck duck) {
		duck.display();
		duck.fly();
	}
	public static void swimSimulation(Duck duck) {
		duck.display();
		duck.swim();
	}
	public static void quackSimulation(Duck duck) {
		duck.display();
		duck.quack();
	}
	public static void main(String[] args) {
		ArrayList<Duck> ducks = new ArrayList<>();
		ducks.add(new MallardDuck(new FlyWithWings(), new QuackWithQuack()));
		ducks.add(new RedHeadDuck(new FlyWithWings(), new QuackWithQuack()));
//		ducks.add(new RedHeadDuck(new FlyWithWings()));
//		ducks.add(new MallardDuck(new FlyWithWings()));
		ducks.add(new RubberDuck(new FlyNoWay(), new QuackNoWay()));
		System.out.println("오리 수영 시뮬레이션");
		for(Duck duck: ducks) swimSimulation(duck);
		System.out.println("오리 꽥꽥 시뮬레이션");
		for(Duck duck: ducks) quackSimulation(duck);
		ducks.get(2).setFlyingStrategy(new FlyNoWay());
		ducks.get(0).setFlyingStrategy(()->System.out.println("로켓으로 날고 있고"));
		System.out.println("오리 비행 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
	}

}
