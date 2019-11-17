/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 팩토리 메소드 패턴 실습
 * AsteroidRectangleFactory
 * 사각형 형태의 소행성 좌표를 생성하는 팩토리 
 * @author 김상진
 */
public class AsteroidRectangleFactory extends AsteroidFactory {

	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		Double[] points = new Double[8];
		points[0] = Double.valueOf(centerLoc.getX()+radius);
		points[1] = Double.valueOf(centerLoc.getY()+radius);
		
		points[2] = Double.valueOf(centerLoc.getX()+radius);
		points[3] = Double.valueOf(centerLoc.getY()-radius);
		
		points[4] = Double.valueOf(centerLoc.getX()-radius);
		points[5] = Double.valueOf(centerLoc.getY()-radius);
		
		points[6] = Double.valueOf(centerLoc.getX()-radius);
		points[7] = Double.valueOf(centerLoc.getY()+radius);
		return points;
	}

}
