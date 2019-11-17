
public class Locked implements DoorState {
	private Door door;
    public Locked(){
        this.door = door;
    }
	@Override
	public boolean open() {
		System.out.println("문이 잠겨 있어 열 수 없음");
		return false;
	}

	@Override
	public boolean close() {
		System.out.println("문이 이미 닫혀 있음");
		return false;
	}

	@Override
	public boolean lock() {
		System.out.println("문이 이미 잠겨 있음");
		return false;
	}

	@Override
	public boolean unlock() {
		System.out.println("문의 잠금을 해제함");
		return true;
	}

}
