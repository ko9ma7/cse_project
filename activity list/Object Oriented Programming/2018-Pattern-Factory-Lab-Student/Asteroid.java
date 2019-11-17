import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 팩토리 매소드 패턴 실습
 * Asteroid
 * 소행성
 * @author 김상진
 */
public class Asteroid extends Polygon{
	private Location startLoc; // 출발지
	private Location destLoc; // 목적지
	
	private int speed;
	public Location getStartLoc() {
		return startLoc;
	}
	public Location getDestLoc() {
		return destLoc;
	}
	public int getSpeed() {
		return speed;
	}
	public Asteroid(Location startLoc, Location destLoc, int speed, Double[] points) {
		this.startLoc = startLoc;
		this.destLoc = destLoc;
		this.speed = speed;
		getPoints().addAll(points);
		setStroke(Color.LIGHTGRAY);
		setFill(null);
		setStrokeWidth(3);
	}
}
