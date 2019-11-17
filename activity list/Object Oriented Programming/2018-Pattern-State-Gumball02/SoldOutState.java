
public class SoldOutState implements GumballState {
	private GumballMachine gMachine;
	public SoldOutState(GumballMachine gMachine){
		this.gMachine = gMachine;
	}
	
	@Override
	public void insertCoin() {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}
	
	@Override
	public void ejectCoin() {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}
	
	@Override
	public void turnCrank() {
		System.out.println("껌볼이 없어 판매가 중단됨");
		
	}
	
	@Override
	public void dispense() {
		System.out.println("껌볼이 없어 판매가 중단됨");
	}
}
