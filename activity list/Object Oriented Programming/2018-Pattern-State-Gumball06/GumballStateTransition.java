/**
 * soldOutState는 singleton으로 구현
 * @author 김상진
 *
 */
public interface GumballStateTransition {
	static final GumballStateEvent soldOutState = SoldOutState.getInstance();
	static final GumballStateEvent soldState = new SoldState();
	static final GumballStateEvent noCoinState = new NoCoinState();
	static final GumballStateEvent hasCoinState = new HasCoinState();
	void changeStateTo(GumballStateEvent state);
}
