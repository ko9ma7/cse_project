
public class HasCoinState implements GumballStateEvent {
	@Override
	public void insertCoin(GumballStateTransition stateTransition) {
		System.out.println("이미 동전이 있음");

	}

	@Override
	public void ejectCoin(GumballStateTransition stateTransition) {
		System.out.println("취소되었음");
		stateTransition.changeStateTo(GumballStateTransition.noCoinState);
	}

	@Override
	public void turnCrank(GumballStateTransition stateTransition) {
		System.out.println("손잡이를 돌렸음");
		stateTransition.changeStateTo(GumballStateTransition.soldState);
	}

	@Override
	public void dispense(GumballStateTransition stateTransition) {
		System.out.println("손잡이를 돌려야 껌볼이 나옴");
	}
}
