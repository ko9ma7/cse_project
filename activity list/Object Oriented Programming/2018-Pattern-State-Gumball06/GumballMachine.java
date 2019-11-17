/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * State Driven Transition (상태 기반 전이)
 * 상태 객체 메소드에 문맥 전달 버전
 * 상태 기반 전이 방식임에도 상태 객체를 공유할 수 있음
 * @author 김상진
 *
 */
public class GumballMachine implements GumballStateTransition{
	private GumballStateEvent currentState;
	private int count = 0;
	
	public void setState(GumballStateEvent nextState){
		currentState = nextState;
	}
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){	
		currentState.insertCoin(this); // 모든 상태 메소드마다 this를 넘김 -> 객체를 공유할 수 있음
	}
	public void ejectCoin(){	
		currentState.ejectCoin(this);
	}
	public void turnCrank(){	
		currentState.turnCrank(this);
		currentState.dispense(this);
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public void dispense(){
		System.out.println(count);
		if(count>0) --count;
	}
	public boolean isEmpty(){
		return (count==0);
	}
	@Override
	public void changeStateTo(GumballStateEvent state) {
		currentState = state;
	}
}
