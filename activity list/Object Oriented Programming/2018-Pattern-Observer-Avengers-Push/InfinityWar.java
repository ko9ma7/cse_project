
public class InfinityWar {

	public static void main(String[] args) {
		
		// Subject 객체 생성
		Avengers avengers = new Avengers();
		
		// Observer 객체 생성 및 추가
		CaptainAmerica captain = new CaptainAmerica(avengers);
		IronMan ironman = new IronMan(avengers);
		Hulk hulk = new Hulk(avengers);
		
		// Observer가 아닌 객체 생성
		Thor thor = new Thor();
		
		// 0. 대기 상태
		System.out.println("===== 대기 상태 =====");
		avengers.setStat(EnemyStatus.NONE); avengers.mesurementChanged();
		
		// 1. 빌런 등장
		System.out.println("===== 빌런 등장 =====");
		avengers.setStat(EnemyStatus.APPEAR); avengers.mesurementChanged();
		
		// 2. 빌런 공격
		System.out.println("===== 빌런 공격 =====");
		avengers.setStat(EnemyStatus.ATTACK); avengers.mesurementChanged();
		
		// Observer 제거 및 추가
		avengers.removeObserver(hulk);
		avengers.addObserver(thor);
		
		// 3. 빌런 제거
		System.out.println("===== 빌런 제거 =====");
		avengers.setStat(EnemyStatus.DISAPPEAR); avengers.mesurementChanged();

	}

}
