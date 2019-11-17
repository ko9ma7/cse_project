/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * State Driven Transition (상태 기반 전이)
 * 상태 객체에 문맥 전달 버전
 * @author 김상진
 *
 */
public class GumballMachine { // GumballMachine은 문맥의 역할 
	private final GumballState soldOutState = new SoldOutState(this); // 이 부분이 상태를 변경
	private final GumballState soldState = new SoldState(this);
	private final GumballState noCoinState = new NoCoinState(this);
	private final GumballState hasCoinState = new HasCoinState(this);
	private GumballState currentState;
	private int count = 0;
	
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
	public void setState(GumballState nextState){
		currentState = nextState;
	}
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){ // 어떤 상태이든, 선택된 문맥의 상태 메소드만 부르면 됨 	
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
