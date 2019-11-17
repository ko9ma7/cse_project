/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * Head First Design Pattern
 * TurkeyClassAdapter: 클래스 어댑터, 기본적으로 양방향으로 사용 가능
 * @author 김상진
 *
 */
public class TurkeyClassAdapter extends WildTurkey implements Duck { // IS-A
	// 이 클래스 어댑터는 무조건 WilldTurkey만 사용가능하다, 다른 클래스는 사용 불가능
	// 오리 쓰는 곳에서 쓸 수 있고, 터키 쓰는 곳에서 쓸 수 있다
	@Override 
	public void quack() {
		gobble();
	}
	@Override
	public void fly() {
		for(int i=0; i<5; i++) super.fly();
	}
}
