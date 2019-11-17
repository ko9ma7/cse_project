/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론
 * Head First Design Pattern
 * TurkeyTwoWayAdapter: 양방향 어댑터
 * fly 메소드???
 * @author 김상진
 *
 */
public class TurkeyTwoWayAdapter implements Turkey, Duck{
	private Turkey turkey; // 터키를 HAS-A로 유지하면서 터키와 오리를 같이 사용가능
	public TurkeyTwoWayAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
	@Override
	public void gobble() {
		turkey.gobble();
	}
	@Override
	public void quack() {
		turkey.gobble();
	}
	@Override // 두 개의 인터페이스 모두 fly라는 기능을 가지고 있기에 하나의 fly만 Override 가능하다
	public void fly() { // 칠면조에서 fly를 하면 조금만 가야되는데 이 메소드가 호출됨(문제점)
		for(int i=0; i<5; i++) turkey.fly();
	}
}
