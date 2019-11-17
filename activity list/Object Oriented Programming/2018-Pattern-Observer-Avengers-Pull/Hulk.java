
public class Hulk implements Observer, Heros {

	public Hulk() {};
	public Hulk(Subject team) {
		System.out.println("헐크 합류");
		team.addObserver(this);
	}
	
	@Override
	public void updateObserver(EnemyStatus stat) {
		this.behavior(stat);
	}

	@Override
	public void behavior(EnemyStatus stat) {
		switch(stat) {
			case NONE: System.out.println("헐크: 대기중"); break;
			case APPEAR: System.out.println("헐크: 변신"); break;
			case ATTACK: System.out.println("헐크: 자아 통제권 상실"); break;
			case DISAPPEAR: System.out.println("헐크: 복귀"); break;
		}
	}
	
}
