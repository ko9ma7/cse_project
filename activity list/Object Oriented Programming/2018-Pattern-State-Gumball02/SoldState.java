
public class SoldState implements GumballState {
	private GumballMachine gMachine;
	public SoldState(GumballMachine gMachine){
		this.gMachine = gMachine;
	}
	
	@Override
	public void insertCoin() {
		System.out.println("동전을 투입할 수 있는 단계가 아님");
	}
	
	@Override
	public void ejectCoin() {
		System.out.println("반환할 동전이 없음");
	}
	
	@Override
	public void turnCrank() {
		System.out.println("이미 손잡이를 돌렸음");
	}
	
	@Override
	public void dispense() {
		System.out.println("껌볼이 나옴");
		gMachine.dispense();
		if(gMachine.isEmpty()){
			System.out.println("껌볼이 더 이상 없습니다.");
			gMachine.setState(gMachine.getSoldOutState());
		}
		else{			
			gMachine.setState(gMachine.getNoCoinState());
		}
	}
}
