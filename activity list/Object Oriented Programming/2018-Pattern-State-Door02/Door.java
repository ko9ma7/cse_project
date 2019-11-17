// 문맥의 구현은 단순하다(상태 전이 방식)
public class Door {
	private final DoorState openedState = new Opened(this);
	private final DoorState closedState = new Closed(this);
	private final DoorState lockedState = new Locked(this);
	private DoorState currentState = closedState;
	public void changeToOpenedState(){
		currentState = openedState;
	}
	public void changeToClosedState(){
		currentState = closedState;
	}
	public void changeToLockedState(){
		currentState = lockedState;
	}
	public void open(){
		currentState.open();
	}
	public void close(){
		currentState.close();
	}
	public void lock(){
		currentState.lock();
	}
	public void unlock(){
		currentState.unlock();
	}
}
