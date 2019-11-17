
public class DoubleSoldState implements GumballState {
	@Override
	public boolean insertCoin() {
		System.out.println("동전을 투입할 수 있는 단계가 아님");
		return false;
	}

	@Override
	public boolean ejectCoin() {
		System.out.println("동전이 없음");
		return false;
	}

	@Override
	public boolean turnCrank() {
		System.out.println("이미 손잡이를 돌렸음");
		return false;
	}

	@Override
	public boolean dispense() {
		System.out.println("껌볼이 2개가 나옴");
		return true;
	}
}
