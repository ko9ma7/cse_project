
public class Stereo {
	private int volume = 0;
	
	public void on(){
		System.out.println("스테리오 전원 켜짐");
	}
	public void off(){
		System.out.println("스테리오 전원 꺼짐");
	}
	public int getVolume() {
		return volume;
	}
	public void setCD(){
		System.out.println("스테리오 입력 CD로 바꿈");
	}
	public void setRadio(){
		System.out.println("스테리오 입력 Radio로 바꿈");
	}
	public void setUSB(){
		System.out.println("스테리오 입력 USB로 바꿈");
	}
	public void setVolume(int volume){
		this.volume = volume;
		System.out.printf("볼륨 조정 %d%n", volume);
	}
}
