
public class Thor implements Observer, Heros {

	public Thor() {};
	public Thor(Subject team) {
		System.out.println("토르 합류");
		team.addObserver(this);
	}
	
	@Override
	public void updateObserver(EnemyStatus stat) {
		this.behavior(stat);
	}

	@Override
	public void behavior(EnemyStatus stat) {
		switch(stat) {
			case NONE: System.out.println("토르: 대기중"); break;
			case APPEAR: System.out.println("토르: 전장 출동 + 묠니르"); break;
			case ATTACK: System.out.println("토르: 묠니르 부서짐"); break;
			case DISAPPEAR: System.out.println("토르: 아스가르드 복귀"); break;
		}
	}
	
}
