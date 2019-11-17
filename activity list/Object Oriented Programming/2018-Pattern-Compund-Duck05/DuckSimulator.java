/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * DuckSimulator.java
 * 복합 패턴
 * 오리를 개별적으로 처리하지 않고 오리떼를 하나의 오리와 동일하게 처리하도록 Composite 패턴 활용
 * @author 김상진
 *
 */
public class DuckSimulator {
	public static void main(String[] args){
		DuckSimulator simulator = new DuckSimulator();
		AbstractDuckFactory duckFactory = new CountingDuckFactory();
		simulator.simulate(duckFactory);
	}
	public void simulate(AbstractDuckFactory duckFactory){
		Quackable mallardDuck = duckFactory.createMallardDuck();
		Quackable redheadDuck = duckFactory.createRedheadDuck();
		// 오리떼 생성
		Flock flockOfDucks = new Flock();
		flockOfDucks.add(mallardDuck);
		flockOfDucks.add(redheadDuck);
		simulate(flockOfDucks);
		Quackable duckCall = duckFactory.createDuckCall();
		simulate(duckCall);
		Quackable rubberDuck = duckFactory.createRubberDuck();
		simulate(rubberDuck);
		Quackable goose = new GooseAdapter(new Goose());
		simulate(goose);
		System.out.printf("꽥꽥 수: %d%n", QuackCounter.getQuacks());	
	}
	private void simulate(Quackable duck){
		duck.quack();
	}
}
