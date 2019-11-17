// ConcreteComponent 클래스 -> 슈트 종류1
public class Mark1 extends Suit {
	
	public Mark1() {
		this.armor = "Mark1";
	}
	
	@Override
	public int getAttackPoint() {
		return 100;
	}

}
