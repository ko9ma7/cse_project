/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 상태 패턴
 * 상태 패턴을 활용하지 않는 버전
 * 각 메소드마다 switch문이 중복됨
 * @author 김상진
 *
 */
public class GumballMachine {
	private GumballState currentState;
	private int count = 0;
	
	public GumballMachine(int numberGumballs) {
		count = numberGumballs;
 		if(count > 0) currentState = GumballState.NO_COIN;
 		else currentState = GumballState.SOLD_OUT;
	}	
	// 현재 상태에서 의미 있는 메소드가 있고, 의미 없는 메소드가 있을 수 있음
	public void insertCoin() { // 동전 투입
		switch(currentState){
		case SOLD_OUT: // 실제 프로그램에서는 의미가 없음
			System.out.println("껌볼이 없어 판매가 중단됨");
			break;
		case NO_COIN: // 의미가 있는 메소드
			System.out.println("동전이 삽입되었음");
			currentState = GumballState.HAS_COIN;
			break;
		case SOLD:
			System.out.println("동전을 투입할 수 있는 단계가 아님");
			break;
		case HAS_COIN:
			System.out.println("이미 동전이 있음");
			break;
		// case DOUBLE_SOLD: // 껌볼이 10% 확률로 두 개가 나오는 경우 상태가 추가된다면, 모든 메소드에 일일이 추가해야함
		}
	}
	public void ejectCoin(){ // 동전 취소
		switch(currentState){
		case SOLD_OUT:
			System.out.println("껌볼이 없어 판매가 중단됨");
			break;
		case NO_COIN:
			System.out.println("반환할 동전 없음");
			break;
		case SOLD:
			System.out.println("반환할 동전이 없음");
			break;
		case HAS_COIN: // 동전이 있는 경우에만 의미가 있음
			System.out.println("삽입된 동전을 반환함");
			currentState = GumballState.NO_COIN;
			break;
		}
	}
	public void turnCrank(){ // 손잡이를 돌리는 경우
		switch(currentState){
		case SOLD_OUT:
			System.out.println("껌볼이 없어 판매가 중단됨");
			break;
		case NO_COIN:
			System.out.println("동전이 없어 손잡이를 돌릴 수 없음");
			break;
		case SOLD:
			System.out.println("이미 손잡이를 돌렸음");
			break;
		case HAS_COIN: // 동전이 있는 경우에만 의미가 있음
			System.out.println("손잡이를 돌렸음");
			currentState = GumballState.SOLD;
			dispense();
			break;
		}
	}
	public void dispense(){ // 껌볼이 제공되는 경우
		switch(currentState){
		case SOLD_OUT:
			System.out.println("껌볼이 없어 판매가 중단됨");
			break;
		case NO_COIN:
			System.out.println("동전을 투입해야 구입할 수 있음");
			break;
		case SOLD:
			System.out.println("껌볼이 나옴");
			--count;
			if(isEmpty()){
				System.out.println("껌볼이 더 이상 없습니다.");
				currentState = GumballState.SOLD_OUT;
			}
			else{			
				currentState = GumballState.NO_COIN;
			}
			break;
		case HAS_COIN:
			System.out.println("손잡이를 돌려야 껌볼이 나옴");
			break;
		}	
	}
	public int getNumberOfGumballs(){
		return count;
	}
	public boolean isEmpty(){
		return (count==0);
	}
}
