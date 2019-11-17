/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * CeilingFan: Receiver 객체
 * @author 김상진
 * 
 */
public class CeilingFan {
	public enum SPEED {OFF, LOW, MEDIUM, HIGH};
	private SPEED speed = SPEED.OFF;

	public void setSpeed(int speed){
		SPEED[] speeds = SPEED.values();
		if(speed>=0&&speed<speeds.length) {
			this.speed = speeds[speed];
			if(speed==0) System.out.println("선풍기 커짐");
			else System.out.printf("선풍기 켜짐. 현재 속도: %d%n", speed);
		}
	}
	public int getSpeed() {
		return speed.ordinal();
	}
}
