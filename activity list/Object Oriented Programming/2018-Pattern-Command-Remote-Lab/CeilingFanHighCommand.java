/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * CeilingFanHighCommand: Concrete Command
 * @author 김상진
 * 
 */
public class CeilingFanHighCommand implements Command {
	private CeilingFan fan;
	private int prevSpeed = 0;
	public CeilingFanHighCommand(CeilingFan fan){
		this.fan = fan;
	}
	@Override
	public void execute() {
		prevSpeed = fan.getSpeed(); // speed의 이전 값을 구하기 위해
		fan.setSpeed(0);
	}
	@Override
	public void undo() {
		fan.setSpeed(prevSpeed); // speed의 상태도 기억하게 만들어준다
	}
}
