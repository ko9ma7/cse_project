public class HasCoinState implements GumballState { // 여기서는 interface로 구현되어 있기 때문에 모든 메소드를 구현해야 하지만, 추상 클래스를 사용하면
													// 필요한 부분만 구현이 가능함. 필요없는 부분은 빈 메소드로 냅두면 됨.
	private GumballMachine gMachine; // 문맥이 has-a관계
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
		gMachine.setState(gMachine.getNoCoinState()); // 상태 객체에서 상태를 변경해줌(NoCoinState)
	}

	@Override
	public void turnCrank() {
		System.out.println("손잡이를 돌렸음");
		gMachine.setState(gMachine.getSoldState()); // soldState로 변경됨
	}

	@Override
	public void dispense() { // 의미 없는 메소드이므로 추상 클래스로 빈 메소드로 구현 가능함
		System.out.println("손잡이를 돌려야 껌볼이 나옴");
	}
}
