
public class CaptainAmerica implements Observer, Heros {

	public CaptainAmerica() {};
	public CaptainAmerica(Subject team) {
		System.out.println("캡틴아메리카 합류");
		team.addObserver(this);
	}
	
	@Override
	public void updateObserver(EnemyStatus stat) {
		this.behavior(stat);
	}
	
	@Override
	public void behavior(EnemyStatus stat) {
		switch(stat) {
			case NONE: System.out.println("캡틴: 대기중"); break;
			case APPEAR: System.out.println("캡틴: 무기장착"); break;
			case ATTACK: System.out.println("캡틴: 정신조종 당함, 전투불가능"); break;
			case DISAPPEAR: System.out.println("캡틴: 무기해제"); break;
	}
	}

}
