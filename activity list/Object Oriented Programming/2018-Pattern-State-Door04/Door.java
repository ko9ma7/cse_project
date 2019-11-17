
public class Door {
	private DoorState currentState = DoorState.CLOSED;
	public void open(){
		currentState = currentState.open();
	}
	public void close(){
		currentState = currentState.close();
	}
	public void lock(){
		currentState = currentState.lock();
	}
	public void unlock(){
		currentState = currentState.unlock();
	}
}
