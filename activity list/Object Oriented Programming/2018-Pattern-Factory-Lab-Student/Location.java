/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 팩토리 메소드 패턴 실습
 * Location
 * 좌표 정보를 유지하는 클래스
 * @author 김상진
 */
public class Location {
	private int x = 0;
	private int y = 0;
	public Location() {	
	}
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
