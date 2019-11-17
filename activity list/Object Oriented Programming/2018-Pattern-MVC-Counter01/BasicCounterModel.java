/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * 2018년도 2학기
 * MVC Pattern
 * @author 김상진
 * BasicCounterModel 클래스: 단순 카운터 데이터를 유지하고 처리하여 주는 클래스
 * MVC 패턴에서 Model에 해당 (Observer 패턴에서 Subject 역할)
 */
import java.util.ArrayList;

public class BasicCounterModel implements CounterModelInterface {
	private int counter = 0;
	private final int MAX;
	private final int INCREMENT;
	private ArrayList<CounterObserver> observers = new ArrayList<CounterObserver>();
	public BasicCounterModel(int max, int step) {
		MAX = max;
		INCREMENT = step;
	}
	@Override
	public void setCounter(int counter) {
		if(counter>=0&&counter<=MAX){
			this.counter = counter;
			notifyObservers();
		}
	}

	@Override
	public void increaseCounter() {
		if(counter<MAX){
			counter += INCREMENT;
			if(counter>MAX) counter = MAX;
			notifyObservers();
		}
	}

	@Override
	public void decreaseCounter() {
		if(counter>0){
			counter -= INCREMENT;
			if(counter<0) counter = 0;
			notifyObservers();
		}
	}

	@Override
	public int getCounter() {
		return counter;
	}
	
	@Override
	public int getMax() {
		return MAX;
	}

	@Override
	public void addObserver(CounterObserver observer) {
		observers.add(observer);
		observer.updateCounter(counter);
	}

	@Override
	public void removeObserver(CounterObserver observer) {
		observers.remove(observer);

	}
	
	@Override
	public void notifyObservers() {
		for(CounterObserver observer: observers)
			observer.updateCounter(counter);
	}
}
