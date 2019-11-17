// return 타입이 boolean -> 상태가 전환되었음을 알림
// 추상 클래스로 만들면 의미 없는 메소드는 모두 false로, 의미 있는 메소드는 모두 true를 반환
public class HasCoinState implements GumballState {
	@Override
	public boolean insertCoin() {
		System.out.println("이미 동전이 있음");
		return false;
	}

	@Override
	public boolean ejectCoin() {
		System.out.println("취소되었음");
		return true;
	}

	@Override
	public boolean turnCrank() {
		System.out.println("손잡이를 돌렸음");
		return true;
	}

	@Override
	public boolean dispense() {
		System.out.println("손잡이를 돌려야 껌볼이 나옴");
		return false;
	}
}
