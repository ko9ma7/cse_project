/**
 * 2018년도 2학기 객체지향개발론및실습
 * 명령패턴: 만능 리모콘 예제
 * Stero: Receiver 객체
 * @author 김상진
 * 
 */
public class Stero {
	public enum InputType {CD, RADIO, USB};
	private InputType currentInput = InputType.CD;
	private int volume = 0;
	
	public void on(){
		System.out.println("스테리오 전원 켜짐");
	}
	public void off(){
		System.out.println("스테리오 전원 꺼짐");
	}
	public InputType getCurrentInput() {
		return currentInput;
	}
	public int getVolume() {
		return volume;
	}
	public void setCD(){
		currentInput = InputType.CD;
		System.out.println("스테리오 입력 CD로 바꿈");
	}
	public void setRadio(){
		currentInput = InputType.RADIO;
		System.out.println("스테리오 입력 Radio로 바꿈");
	}
	public void setUSB(){
		currentInput = InputType.USB;
		System.out.println("스테리오 입력 USB로 바꿈");
	}
	public void setVolume(int volume){
		this.volume = volume;
		System.out.printf("볼륨 조정 %d%n", volume);
	}
}
