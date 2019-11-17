// ConcreteComponent 클래스 -> 슈트 종류2
public class Mark2 extends Suit {

	public Mark2() {
		this.armor = "Mark2";
	}
	
	@Override
	public int getAttackPoint() {
		return 200;
	}
}
