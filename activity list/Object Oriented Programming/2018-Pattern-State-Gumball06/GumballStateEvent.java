
public interface GumballStateEvent {
	void insertCoin(GumballStateTransition stateTransition);
	void ejectCoin(GumballStateTransition stateTransition);
	void turnCrank(GumballStateTransition stateTransition);
	void dispense(GumballStateTransition stateTransition);
}
