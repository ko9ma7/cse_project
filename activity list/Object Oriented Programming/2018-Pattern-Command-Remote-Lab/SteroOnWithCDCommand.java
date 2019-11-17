/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * SteroOnWithCDCommand: Concrete Command
 * @author 김상진
 * 
 */
public class SteroOnWithCDCommand implements Command {
	private Stero stero;
	public SteroOnWithCDCommand(Stero stero){
		this.stero = stero;
	}
	@Override
	public void execute() {
		stero.on();	
		stero.setCD();
		stero.setVolume(11);
	}
	@Override
	public void undo() {
		stero.off();
	}
}
