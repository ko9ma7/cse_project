
public class SoldState implements GumballStateEvent {
	@Override
	public void insertCoin(GumballStateTransition stateTransition) {
		System.out.println("동전을 투입할 수 있는 단계가 아님");
	}

	@Override
	public void ejectCoin(GumballStateTransition stateTransition) {
		System.out.println("동전이 없음");

	}

	@Override
	public void turnCrank(GumballStateTransition stateTransition) {
		System.out.println("이미 손잡이를 돌렸음");
	}

	@Override
	public void dispense(GumballStateTransition stateTransition) {
		System.out.println("껌볼이 나옴");
		((GumballMachine)stateTransition).dispense();
		if(((GumballMachine)stateTransition).isEmpty()){
			System.out.println("껌볼이 더 이상 없습니다.");
			stateTransition.changeStateTo(GumballStateTransition.soldOutState);
		}
		else{
			stateTransition.changeStateTo(GumballStateTransition.noCoinState);
		}
	}

}
