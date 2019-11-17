
public class SoldOutState implements GumballState {
	@Override
	public boolean insertCoin() {
		System.out.println("껌볼이 없어 판매가 중단됨");
		return false;
	}

	@Override
	public boolean ejectCoin() {
		System.out.println("껌볼이 없어 판매가 중단됨");
		return false;
	}

	@Override
	public boolean turnCrank() {
		System.out.println("껌볼이 없어 판매가 중단됨");
		return false;
	}

	@Override
	public boolean dispense() {
		System.out.println("껌볼이 없어 판매가 중단됨");
		return false;
	}
}
