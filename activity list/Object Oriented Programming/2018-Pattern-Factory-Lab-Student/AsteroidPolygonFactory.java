/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 팩토리 메소드 패턴 실습
 * AsteroidPolygonFactory
 * 다각형 형태의 소행성 좌표를 생성하는 팩토리 
 * @author 김상진
 */
public class AsteroidPolygonFactory extends AsteroidFactory {
	@Override
	public Double[] createPoints(Location centerLoc, int radius) {
		Double[] points = new Double[32];
		
		points[0] = Double.valueOf(centerLoc.getX());
		points[1] = Double.valueOf(centerLoc.getY()+radius * 2);
		
		points[2] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(60)) * 1.3);
		points[3] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(60)));
		
		points[4] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(45)) * 1.4);
		points[5] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(45)));
		
		points[6] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(30)) * 1.5);
		points[7] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(30)));
		
		points[8] = Double.valueOf(centerLoc.getX()+radius * 2);
		points[9] = Double.valueOf(centerLoc.getY());
		
		points[10] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(30)) * 1.7);
		points[11] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(30)));
		
		points[12] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(45)) * 1.5);
		points[13] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(45)));
		
		points[14] = Double.valueOf(centerLoc.getX()+radius * Math.cosh(Math.toRadians(60)));
		points[15] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(60)) * 1.4);
		
		points[16] = Double.valueOf(centerLoc.getX());
		points[17] = Double.valueOf(centerLoc.getY()-radius * 2);
		
		points[18] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(60)) * 1.2);
		points[19] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(60)));
		
		points[20] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(45)) * 1.5);
		points[21] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(45)));
		
		points[22] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(30)) * 1.7);
		points[23] = Double.valueOf(centerLoc.getY()-radius * Math.sinh(Math.toRadians(30)));
		
		points[24] = Double.valueOf(centerLoc.getX()-radius * 2);
		points[25] = Double.valueOf(centerLoc.getY());
		
		points[26] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(30)) * 1.7);
		points[27] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(30)));
		
		points[28] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(45)) * 1.5);
		points[29] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(45)));
		
		points[30] = Double.valueOf(centerLoc.getX()-radius * Math.cosh(Math.toRadians(60)) * 1.2);
		points[31] = Double.valueOf(centerLoc.getY()+radius * Math.sinh(Math.toRadians(60)));
	
		return points;
	}
}
