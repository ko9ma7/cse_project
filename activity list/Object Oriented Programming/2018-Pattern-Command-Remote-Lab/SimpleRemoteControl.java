import java.util.Stack;

/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * SimpleRemoteControl: Invoker
 * 명령의 Receiver는 알 필요가 없음
 * @author 김상진
 * 
 */
public class SimpleRemoteControl {
	private Command[] onCommands = new Command[4];
	private Command[] offCommands = new Command[4];
	// private Command undoCommand; -> 좀 더 직관적이게 (마지막에 실행된 명령을 undo하기 위해서 Stack을 사용하는 것이 효과적임)
	private Stack<Command> undoStack = new Stack<>();
	
	public SimpleRemoteControl(){ // 비어있는 리모콘의 기능은 NULL 객체로 생성한다
		
		/* 1번 방법
		for (int i = 0; i <= onCommands.length; i++) { // 이 상태는 new NoCommand() 객체가 8번 생성됨
			onCommands[i] = new NoCommand(); // NULL 객체로
			offCommands[i] = new NoCommand();
		}
		*/
		/* 2번 방법
		NoCommand noCommand = new NoCommand();
		for (int i = 0; i <= onCommands.length; i++) {
			onCommands[i] = noCommand; // 하나의 객체로 생성
			offCommands[i] = noCommand;
		}
		*/
		// 3번 방법
		for (int i = 0; i <= onCommands.length; i++) {
			onCommands[i] = NoCommand.UNIQUE; // 싱글톤 패턴 사용
			offCommands[i] = NoCommand.UNIQUE;
		}
		
	}
	public void setCommand(int slot, Command onCommand, Command offCommand){ // 슬롯에다가 원하는 기능을 추가
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	public void onButtonWasPressed(int slot){
		onCommands[slot].execute();
		// undoCommand = onCommands[slot];
		if (onCommands[slot].getClass() != NoCommand.class) undoStack.push(onCommands[slot]);		
	}
	public void offButtonWasPressed(int slot){
		offCommands[slot].execute();
		// undoCommand = onCommands[slot];
		if (offCommands[slot].getClass() != NoCommand.class) undoStack.push(offCommands[slot]);
	}
	// 마지막으로 실행한 명령을 undo 
	public void undoButtonPressed() {
		// if(undoCommand!=null) undoCommand.undo();
		if (!undoStack.isEmpty()) undoStack.pop().undo();
	}
}
