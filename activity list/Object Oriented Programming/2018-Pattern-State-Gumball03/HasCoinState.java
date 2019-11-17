import java.util.Random;

public class HasCoinState implements GumballState {
	private GumballMachine gMachine;
	private static final Random randomWinner = new Random(System.currentTimeMillis());
	public HasCoinState(GumballMachine gMachine){
		this.gMachine = gMachine;
	}
	
	@Override
	public void insertCoin() {
		System.out.println("이미 동전이 있음");

	}

	@Override
	public void ejectCoin() {
		System.out.println("취소되었음");
		gMachine.setState(gMachine.getNoCoinState());
	}

	// GumballMachine01보다 좀 더 복잡해짐(상태가 추가되면서 '동전 있음' -> '우승'으로 가는 행위에 대해 코드 변경이 불가피함)
	@Override
	public void turnCrank() {
		System.out.println("손잡이를 돌렸음");
		// changed
		int winner = randomWinner.nextInt(10);
		if (winner<5) {
			gMachine.setState(gMachine.getDoubleSoldState());
		} else {
			gMachine.setState(gMachine.getSoldState());
		}
	}

	@Override
	public void dispense() {
		System.out.println("손잡이를 돌려야 껌볼이 나옴");
	}
}
