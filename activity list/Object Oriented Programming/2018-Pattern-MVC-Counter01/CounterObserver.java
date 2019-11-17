/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * CounterObserver 인터페이스:  
 *   모델을 관찰하고 싶은 observer 클래스가 제공해야 하는 인터페이스
 */

public interface CounterObserver {
	void updateCounter(int currentValue);
}
