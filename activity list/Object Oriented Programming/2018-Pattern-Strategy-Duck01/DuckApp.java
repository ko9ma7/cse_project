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
		ducks.add(new MallardDuck());
		ducks.add(new RedHeadDuck());
//		ducks.add(new RedHeadDuck());
//		ducks.add(new MallardDuck());
		ducks.add(new RubberDuck());
		System.out.println("오리 수영 시뮬레이션");
		for(Duck duck: ducks) swimSimulation(duck);
		System.out.println("오리 꽥꽥 시뮬레이션");
		for(Duck duck: ducks) quackSimulation(duck);
		System.out.println("오리 비행 시뮬레이션");
		for(Duck duck: ducks) flySimulation(duck);
	}

}
