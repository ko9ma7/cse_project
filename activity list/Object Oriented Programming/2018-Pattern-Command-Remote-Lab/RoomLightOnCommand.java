/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * RoomLightOnCommand: Concrete Command
 * @author 김상진
 * 
 */
public class RoomLightOnCommand implements Command {
	private RoomLight light;
	public RoomLightOnCommand(RoomLight light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.on();
	}
	@Override
	public void undo() {
		light.off();
	}
}
