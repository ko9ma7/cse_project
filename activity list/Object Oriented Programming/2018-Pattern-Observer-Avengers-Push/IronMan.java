// 히어로 클래스 : 각각의 히어로 클래스들은 Heros 인터페이스를 구현하고 각 상태에 대한 행동을 정의한다
public class IronMan implements Observer, Heros {

	public IronMan() {};
	public IronMan(Subject team) {
		System.out.println("아이언맨 합류");
		team.addObserver(this);
	}

	@Override
	public void updateObserver(EnemyStatus stat) {
		this.behavior(stat);
	}
	
	@Override
	public void behavior(EnemyStatus stat) {
		switch(stat) {
			case NONE: System.out.println("아이언맨: 대기중"); break;
			case APPEAR: System.out.println("아이언맨: 수트 착용"); break;
			case ATTACK: System.out.println("아이언맨: 해킹당함, 전투불가능"); break;
			case DISAPPEAR: System.out.println("아이언맨: 수트 해제"); break;
		}
	}

}
