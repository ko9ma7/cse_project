
public interface DoorState {
	default void open() {}
//	void open();
    void close();
    void lock();
    void unlock();
}
