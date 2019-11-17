// Invoker 클래스
public class SimpleRemoteControl {
	
	Command slot;
	
	public SimpleRemoteControl() {}
	
	public void setCommand(Command command) {
		slot = command;
	}
	
	public void isButtonPressed() {
		slot.execute();
	}
	
}
