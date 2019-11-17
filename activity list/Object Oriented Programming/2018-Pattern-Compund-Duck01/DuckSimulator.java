/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * DuckSimulator.java
 * 복합 패턴
 * 오리 예제를 검사하는 프로그램
 * @author 김상진
 *
 */
public class DuckSimulator {
	public static void main(String[] args){
		DuckSimulator simulator = new DuckSimulator();
		simulator.simulate();
	}
	public void simulate(){
		Quackable mallardDuck = new MallardDuck();
		simulate(mallardDuck);
		Quackable redheadDuck = new RedheadDuck();
		simulate(redheadDuck);
		Quackable duckCall = new DuckCall();
		simulate(duckCall);
		Quackable rubberDuck = new RubberDuck();
		simulate(rubberDuck);
	}
	private void simulate(Quackable duck){
		duck.quack();
	}
}
