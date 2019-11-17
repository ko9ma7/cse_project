/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * RoomLight: Receiver 객체
 * @author 김상진
 * 
 */
public class RoomLight {
	private String location;
	public RoomLight(String location) {
		this.location = location;
	}
	public void on(){
		System.out.printf("%s: 불이 켜짐%n", location);	
	}
	public void off(){
		System.out.printf("%s: 불이 꺼짐%n", location);
	}
}
