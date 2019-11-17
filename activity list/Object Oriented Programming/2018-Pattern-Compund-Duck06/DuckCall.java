/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * DuckCall.java
 * 복합 패턴
 * 오리 소리 흉내 클래스 
 * 장난감 고무오리: 관찰자 기능을 구현하지 않음 
 * @author 김상진
 *
 */
public class DuckCall implements Quackable {

	@Override
	public void quack() {
		System.out.println("꽥액액~~");
	}

}
