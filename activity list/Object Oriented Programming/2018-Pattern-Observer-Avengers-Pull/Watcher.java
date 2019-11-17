// PULL 방식을 이용할 수 있는 옵저버 객체를 구현
public class Watcher implements Observer {

	Subject subject;
	
	public Watcher(Subject subject) {
		this.subject = subject;
		this.subject.addObserver(this);
	}
	
	public void checkStat() {
		System.out.println("===== 상태 체크 =====");
		subject.notifyObserver(this); // 데이터 전달
	}
	
	@Override
	public void updateObserver(EnemyStatus stat) {
		switch(stat) {
			case NONE: System.out.println("(와처: 대기중)"); break;
			case APPEAR: System.out.println("(와처: 빌런등장 기록)"); break;
			case ATTACK: System.out.println("(와처: 빌런공격 기록)"); break;
			case DISAPPEAR: System.out.println("(와처: 빌런제거 기록)"); break;
		}
	}
	
	
}
