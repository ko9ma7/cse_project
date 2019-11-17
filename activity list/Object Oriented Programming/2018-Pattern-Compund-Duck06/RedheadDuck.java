/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * ReadheadDuck.java
 * 복합 패턴
 * 붉은머리오리
 * 관찰자 패턴에서 Subject 기능 추가 (코드 중복)
 * @author 김상진
 *
 */
public class RedheadDuck implements Quackable {
	private Observable observers = new Observable(this);
	@Override
	public void quack() {
		System.out.println("꽥꽥");
		notifyObservers();
	}
	@Override
	public void registerObserver(Observer observer) {
		observers.registerObserver(observer);
	}
	@Override
	public void notifyObservers() {
		observers.notifyObservers();
	}
}
