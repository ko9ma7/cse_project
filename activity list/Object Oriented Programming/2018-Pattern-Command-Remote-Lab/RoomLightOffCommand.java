/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * RoomLightOffCommand: Concrete Command
 * @author 김상진
 * 
 */
public class RoomLightOffCommand implements Command {
	private RoomLight light;
	public RoomLightOffCommand(RoomLight light) {
		this.light = light;
	}
	@Override
	public void execute() {
		light.off();
	}
	@Override
	public void undo() {
		light.on();
	}
}
