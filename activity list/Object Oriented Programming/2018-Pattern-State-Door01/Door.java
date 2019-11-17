
public class Door {
	public enum State {OPENED, CLOSED, LOCKED}
	private State currentState = State.CLOSED;
	public void open(){
		switch(currentState){
		case OPENED:
			System.out.println("이미 열려 있음");
			break;
		case CLOSED:
			System.out.println("문이 열림");
			currentState = State.OPENED;
			break;
		case LOCKED:
			System.out.println("문이 잠겨 있어 열 수 없음");
			break;
		} // switch
	}
	public void close(){
		switch(currentState){
		case OPENED:
			System.out.println("문이 닫힘");
			currentState = State.CLOSED;
			break;
		case CLOSED:
		case LOCKED:
			System.out.println("문이 이미 닫혀 있음");
			break;
		} // switch
	}
	public void lock(){
		switch(currentState){
		case OPENED:
			System.out.println("문이 열려 있어 잠글 수 없음");
			break;
		case CLOSED:
			System.out.println("문을 잠금");
			currentState = State.LOCKED;
			break;
		case LOCKED:
			System.out.println("문이 이미 잠겨 있음");
			break;
		} // switch
	}
	public void unlock(){
		switch(currentState){
		case OPENED:
		case CLOSED:
			System.out.println("문이 잠겨 있지 않음");
			break;
		case LOCKED:
			System.out.println("문의 잠금을 해제함");
			currentState = State.CLOSED;
			break;
		} // switch
	}
}
