/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * SteroOffCommand: Concrete Command
 * @author 김상진
 * 
 */

// SteroOffCommand 하나만을 선언하여 SteroOnCommand도 표현한다(undo)

public class SteroOffCommand implements Command {
	private Stero stero;
	private int prevVolume;
	private Stero.InputType prevType = Stero.InputType.CD;
	
	public SteroOffCommand(Stero stero){
		this.stero = stero;
	}
	@Override
	public void execute() {
		prevVolume = stero.getVolume();
		prevType = stero.getCurrentInput();
		stero.off(); // stero의 상태를 가지고 있어야함(volume, USB, ...)
		
	}
	@Override
	public void undo() {
		stero.on();
		
		switch(prevType) {
		case CD: stero.setCD(); break;
		case RADIO: stero.setRadio(); break;
		case USB: stero.setUSB(); break;
		}
		stero.setVolume(prevVolume);
	}
}
