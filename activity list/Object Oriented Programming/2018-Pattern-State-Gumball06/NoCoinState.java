
public class NoCoinState implements GumballStateEvent {
	@Override
	public void insertCoin(GumballStateTransition stateTransition) {
		System.out.println("동전이 삽입되었");
		stateTransition.changeStateTo(GumballStateTransition.hasCoinState);
	}

	@Override
	public void ejectCoin(GumballStateTransition stateTransition) {
		System.out.println("반환할 동전 없음");
	}

	@Override
	public void turnCrank(GumballStateTransition stateTransition) {
		System.out.println("동전이 없어 손잡이를 돌릻 수 없음");
	}

	@Override
	public void dispense(GumballStateTransition stateTransition) {
		System.out.println("동전을 투입해야 구입할 수 있음");
	}

}
