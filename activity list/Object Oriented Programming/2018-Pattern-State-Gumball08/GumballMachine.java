/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * GumballMachine.java
 * 문맥 클래스
 * State Driven Transition (상태 기반 전이)
 * 열거형으로 상태 객체들을 정의. 한 자바 파일에 모든 상태 구현.
 * 상태 객체의 메소드가 다음 상태를 반환 
 * @author 김상진
 *
 */
// 상태를 반환해줌, 어차피 한 파일에 구현되므로 독립적으로 생각할 필요 없기 때문
// 1. 가장 깔끔한 구조
// 2. 상태기반이지만 상태 객체들이 열거형으로 자동적으로 생성(상태를 반환하게 끔 만들어줌) --- ★)
// 3. 한 파일에 모든 코드를 볼 수 있음
// 4. 문맥에 있는 코드에서 if문이 필요가 없음
public class GumballMachine {
	private GumballState currentState;
	private int count = 0;	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = GumballState.NOCOINSTATE;
 		else currentState = GumballState.SOLDOUTSTATE;
	}	
	public void insertCoin(){	
		currentState = currentState.insertCoin();
	}
	public void ejectCoin(){	
		currentState = currentState.ejectCoin(); // 변했으면 올바른 상태로 변함, 코드를 그냥 불러서 생성
	}
	public void turnCrank(){	
		currentState = currentState.turnCrank();
		currentState = currentState.dispense();
		if(currentState==GumballState.SOLDSTATE){
			dispense();
			if(count==0){
				System.out.println("껌볼이 더 이상 없습니다.");
				currentState = GumballState.SOLDOUTSTATE;
			}
			else{			
				currentState = GumballState.NOCOINSTATE;
			}
		}
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public void dispense(){
		if(count>0) --count;
	}
}
