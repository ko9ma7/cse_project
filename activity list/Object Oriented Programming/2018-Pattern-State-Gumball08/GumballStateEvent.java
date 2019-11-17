
public interface GumballStateEvent {
	GumballState insertCoin();
	GumballState ejectCoin();
	GumballState turnCrank();
	GumballState dispense();
}
