/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * Head First Design Pattern
 * TurkeyObjectAdapter: 객체 어댑터
 * @author 김상진
 *
 */
public class TurkeyObjectAdapter implements Duck { // HAS-A
	private Turkey turkey; // 터키 객체를 멤버로 가져야 함
	public TurkeyObjectAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	@Override
	public void quack() { // 구현
		turkey.gobble();
	}

	@Override
	public void fly() { // 단순히 어댑터를 할 때, 좀 더 기능을 추가하고자
		for(int i=0; i<5; i++) turkey.fly();
	}
}
