
public class SoldOutState implements GumballStateEvent {
	private static final SoldOutState instance = new SoldOutState();
	private SoldOutState(){}
	public static SoldOutState getInstance(){
		return instance;
	}
	@Override
	public void insertCoin(GumballStateTransition stateTransition) {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}

	@Override
	public void ejectCoin(GumballStateTransition stateTransition) {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}

	@Override
	public void turnCrank(GumballStateTransition stateTransition) {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}

	@Override
	public void dispense(GumballStateTransition stateTransition) {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}
}
