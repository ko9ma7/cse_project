
public class Closed implements DoorState {
	private Door door;
    public Closed(Door door){
        this.door = door;
    }
	@Override
	public void open() {
		System.out.println("문이 열림");
		door.changeToOpenedState();
	}

	@Override
	public void close() {
		System.out.println("문이 이미 닫혀 있음");
		
	}

	@Override
	public void lock() {
		System.out.println("문을 잠금");
		door.changeToLockedState();
	}

	@Override
	public void unlock() {
		System.out.println("문이 잠겨 있지 않음");
	}

}
