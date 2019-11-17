import java.util.Random;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 팩토리 메소드 패턴 실습
 * AsteroidFactory
 * 소행성을 생성하는 팩토리 
 * @author 김상진
 */
public abstract class AsteroidFactory {
	public static final Random randomGen = new Random();
	// 이동하는 속도는 동일구간을 이동하는데 걸리는 ms로 표현됨 
	// 따라서 값이 작을 수록 빠르게 움직임
	// 레벨이 증가할 수록 빠르게 이동활 확률이 높아짐
	protected int getSpeed(int level) {
		int speedProb = level*3+15;
		int speed = randomGen.nextInt(200);
		int currProb = randomGen.nextInt(100);
		if(currProb<=speedProb/2) {
			speed += 1000;
		}
		else if(currProb<=speedProb) {
			speed += 1500;
		}
		else if(currProb<=speedProb*2) {
			speed += 2000;
		}
		else speed += 2500; // 속도가 증가할 수록 시간이 증가하는 것이므로 느려지는 것!
		return speed;
	}
	// 소행성의 크기는 원 개념을 이용하여 반지름으로 나타냄
	// 레벨이 높을 수록 크기가 커질 확률이 높아짐
	protected int getRadius(int level) {
		int radiusProb = level*3+15;
		int radius = randomGen.nextInt(10);
		int currProb = randomGen.nextInt(100);
		if(currProb<=radiusProb/2) {
			radius += 90;
		}
		else if(currProb<=radiusProb) {
			radius += 60;
		}
		else if(currProb<=radiusProb*2) {
			radius += 30;
		}
		else radius += 15;
		return radius;
	}
	protected Location getStartLocation(int radius) {
		switch(randomGen.nextInt(4)) {
		case 0: // NORTH
			return new Location(randomGen.nextInt(AsteroidsGame.WIDTH),-radius);
		case 1: // WEST
			return new Location(-radius, randomGen.nextInt(AsteroidsGame.HEIGHT));
		case 2: // SOUTH
			return new Location(
				randomGen.nextInt(AsteroidsGame.WIDTH),radius+AsteroidsGame.HEIGHT);
		default: // EAST
			return new Location(
				AsteroidsGame.WIDTH+radius, randomGen.nextInt(AsteroidsGame.HEIGHT));
		}
	}
	protected Location getDestLocation(Location startLoc, int radius) { // 시작위치가 정해지면 목적지는 무조건 반대편으로
		if(startLoc.getY()==-radius) // SOUTH
			return new Location(randomGen.nextInt(AsteroidsGame.WIDTH), radius+AsteroidsGame.HEIGHT);
		else if(startLoc.getX()==-radius) // EAST
			return new Location(AsteroidsGame.WIDTH+radius, randomGen.nextInt(AsteroidsGame.HEIGHT));
		else if(startLoc.getY()>=AsteroidsGame.HEIGHT) // NORTH
			return new Location(randomGen.nextInt(AsteroidsGame.WIDTH+radius),-radius);
		else // WEST
			return new Location(-radius, randomGen.nextInt(AsteroidsGame.HEIGHT));
	}
	// 생성 프레임워크 (template method)
	public final Asteroid createAsteroid(int level) {
		int radius = getRadius(level);
		Location startLoc = getStartLocation(radius);
		Location destLoc = getDestLocation(startLoc, radius);
		Double[] points = createPoints(startLoc, radius);
		return new Asteroid(startLoc, destLoc, getSpeed(level), points);
	}
	// 자식들이 구현해야 하는 팩토리 메소드의 일부분 
	protected abstract Double[] createPoints(Location centerLoc, int radius);
}
