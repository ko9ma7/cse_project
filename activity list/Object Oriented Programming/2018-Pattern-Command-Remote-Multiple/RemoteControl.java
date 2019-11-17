// Invoker 클래스
public class RemoteControl {
	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand; // 취소 기능
	
	public RemoteControl() {
		onCommands = new Command[7];
		offCommands = new Command[7];
		Command noCommand = new NoCommand(); // 아무 동작도 없는 커맨드 객체
		
		for (int i = 0; i < 7; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand) {
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	
	public void isOnButtonPressed(int slot) {
		onCommands[slot].execute();
		undoCommand = onCommands[slot];
	}
	
	public void isOffButtonPressed(int slot) {
		offCommands[slot].execute();
		undoCommand = offCommands[slot];
	}
	
	public void isUndoButtonPressed() {
		System.out.println("취소");
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 7; i++) {
			sb.append(onCommands[i].getClass().getName() + " /"
					+ offCommands[i].getClass().getName() + "\n");
		}
		
		return sb.toString();
	}
}
