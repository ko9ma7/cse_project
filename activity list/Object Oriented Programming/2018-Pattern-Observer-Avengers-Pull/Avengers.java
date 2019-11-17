import java.util.ArrayList;

// Avengers 클래스 : Subject 인터페이스를 상속받아 Observer 인터페이스의 구현체(히어로 클래스)를 관리하고 상태를 업데이트
public class Avengers implements Subject {

	private ArrayList<Observer> heros = new ArrayList<>();
	private EnemyStatus stat;
	
	@Override
	public void addObserver(Observer observer) {
		if (observer!= null) heros.add(observer);
	}
	
	@Override
	public void removeObserver(Observer observer) {
		if (heros.contains(observer)) heros.remove(observer);
	}
	
	@Override
	public void notifyObserver() {
		for (Observer hero : heros) hero.updateObserver(stat);
	}
	
	public void mesurementChanged() {
		notifyObserver();
	}
	
	// 상태 변경이 있을 때 옵저버들에게 알림
	public void setStat(EnemyStatus stat) {
		this.stat = stat;
		// mesurementChanged();
	}
	
	public ArrayList<Observer> getMembers() {
		return this.heros;
	}

	@Override
	public void notifyObserver(Observer observer) {
		observer.updateObserver(stat);
	}
	
}
