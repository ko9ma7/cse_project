/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * Context driven transition (문맥 기반 전이) 
 * 상태 객체에 문맥을 전달할 필요가 없음
 * 상태 객체들을 공유할 수 있는 장점이 있음
 * 문맥 객체는 상태 기반 전이 방식보다 복잡해짐
 * 이유. 한 상태에서 상황에 따라 여러 상태로 전이될 경우에는 문맥 기반 전이는 구현이 번거로울 수 있음
 * 이유. 관련하여 조건문이 많아질 수 있음 
 * @author 김상진
 *
 */
public class GumballMachine {
	// private final GumballState soldOutState = new SoldOutState(this);
	private static final GumballState soldOutState = new SoldOutState(); // 문맥에서 전이하므로 this를 전달할 이유가 없음
	private static final GumballState soldState = new SoldState();       // 어떤 문맥을 처리하는지 알 필요가 없기 때문에
	private static final GumballState noCoinState = new NoCoinState();   // static -> 모든 문맥이 상태를 공유할 수 있음
	private static final GumballState hasCoinState = new HasCoinState(); // 문맥 기반에서는 상태 객체를 공유할 수 있음(싱글톤 패턴과 비슷함)
	private GumballState currentState;
	private int count = 0;
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){ // insertCoin에서 true가 오면, hasCoinState 상태로 변화되어야 하는구나!	
		if(currentState.insertCoin()) currentState = hasCoinState;
	}
	public void ejectCoin(){	
		if(currentState.ejectCoin()) currentState = noCoinState;
	}
	public void turnCrank(){	
		if(currentState.turnCrank()) currentState = soldState;
		if(currentState.dispense()){
			dispense();
			if(count==0){
				System.out.println("껌볼이 더 이상 없습니다.");
				currentState = soldOutState;
			}
			else{			
				currentState = noCoinState;
			}
		}
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public void dispense(){
		if(count>0) --count;
		System.out.println(count);
	}
}
