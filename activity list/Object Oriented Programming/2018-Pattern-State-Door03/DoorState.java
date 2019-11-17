
public interface DoorState {
	default boolean open() { return false; }
//	boolean open();
	boolean close();
	boolean lock();
	boolean unlock();
}
