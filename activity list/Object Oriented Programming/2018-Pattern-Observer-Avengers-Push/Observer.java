// Observer 인터페이스 : 상태 업데이트 시 사용할 메소드를 선언
public interface Observer {

	public void updateObserver(EnemyStatus stat); // 옵저버들의 상태 업데이트
	
}
