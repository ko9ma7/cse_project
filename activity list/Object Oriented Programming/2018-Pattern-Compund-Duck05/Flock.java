import java.util.ArrayList;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * Flock.java
 * 복합 패턴: Composite 패턴
 * 오리떼를 오리와 동일하게 취급할 수 있도록 해줌
 * @author 김상진
 *
 */
public class Flock implements Quackable {
	private ArrayList<Quackable> quackers = new ArrayList<>();
	public void add(Quackable duck){
		quackers.add(duck);
	}
	@Override
	public void quack(){
		for(Quackable duck: quackers){
			duck.quack();
		}
	}

}
