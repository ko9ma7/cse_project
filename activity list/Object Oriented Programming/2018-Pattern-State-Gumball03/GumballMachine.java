/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * State Driven Transition (상태 기반 전이)
 * 상태 객체에 문맥 전달 버전
 * DoubleSold 상태 추가 버전
 * @author 김상진
 *
 */
// GumballMachine02에서 껌볼이 2개가 나오는 상태가 추가된 경우
// 상태가 추가되도 나머지 상태 메소드는 변하지 않아야 함
public class GumballMachine {
	private final GumballState soldOutState = new SoldOutState(this);
	private final GumballState soldState = new SoldState(this);
	private final GumballState noCoinState = new NoCoinState(this);
	private final GumballState hasCoinState = new HasCoinState(this);
	// added
	private final GumballState doubleSoldState = new DoubleSoldState(this);
	
	private GumballState currentState;
	private int count = 0;
	
	/*
	public void changeToSoldOutState(){
		currentState = soldOutState;
	} 
	*/
	public GumballState getSoldOutState() {
		return soldOutState;
	}
	public GumballState getSoldState() {
		return soldState;
	}
	public GumballState getNoCoinState() {
		return noCoinState;
	}
	public GumballState getHasCoinState() {
		return hasCoinState;
	}
	// added
	public GumballState getDoubleSoldState() {
		return doubleSoldState;
	}
	public void setState(GumballState nextState){
		currentState = nextState;
	}
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){	
		currentState.insertCoin();
	}
	public void ejectCoin(){	
		currentState.ejectCoin();
	}
	public void turnCrank(){	
		currentState.turnCrank();
		currentState.dispense();
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public void dispense(){
		if(count>0) --count;
		System.out.println(count);  
	}
	public boolean isEmpty(){
		return (count==0);
	}
}
