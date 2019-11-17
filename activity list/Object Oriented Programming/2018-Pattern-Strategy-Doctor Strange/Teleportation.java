// 이동 방법 인터페이스 구현 - 기술: 순간이동
public class Teleportation implements Move {

	@Override
	public void doMove() {
		System.out.println("이동 방법: 텔레포트");
	}
	
}
