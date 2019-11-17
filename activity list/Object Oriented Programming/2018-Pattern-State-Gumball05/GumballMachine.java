import java.util.Random;

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
 * DoubleSold 상태 추가 버전
 * @author 김상진
 *
 */
// GumballMachine04에서 껌볼이 2개가 나오는 상태를 추가한 경우

public class GumballMachine {
	private static final GumballState soldOutState = new SoldOutState();
	private static final GumballState soldState = new SoldState();
	private static final GumballState noCoinState = new NoCoinState();
	private static final GumballState hasCoinState = new HasCoinState();
	// added
	private static final GumballState doubleSoldState = new DoubleSoldState();
	private static final Random randomWinner = new Random(System.currentTimeMillis());
	
	private GumballState currentState;
	private int count = 0;
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = noCoinState;
 		else currentState = soldOutState;
	}	
	public void insertCoin(){	
		if(currentState.insertCoin()) currentState = hasCoinState;
	}
	public void ejectCoin(){	
		if(currentState.ejectCoin()) currentState = noCoinState;
	}
	public void turnCrank(){ // 이 부분이 복잡해짐
		// changed
		if(currentState.turnCrank()){
			int winner = randomWinner.nextInt(10);
			if (winner<5) currentState = doubleSoldState;
			else currentState = soldState;	
		}
		if(currentState.dispense()){
			dispense();
			if(count>=1&&currentState==doubleSoldState)
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
		System.out.println(count);
		if(count>0) --count;
	}
}
