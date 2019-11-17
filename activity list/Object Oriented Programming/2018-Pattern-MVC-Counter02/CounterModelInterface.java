/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * CounterModelInterface 인터페이스: 단순 카운터 데이터를 유지하는 모델이 제공해야 하는 인터페이스
 */
public interface CounterModelInterface {
	void setCounter(int counter);
	void increaseCounter();
	void decreaseCounter();
	int getCounter();
	int getMax();
}
