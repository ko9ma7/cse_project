// 이동 방법 인터페이스 구현 - 기술: 비행
public class Fly implements Move {

	@Override
	public void doMove() {
		System.out.println("이동 방법: 비행");
	}
	
}
