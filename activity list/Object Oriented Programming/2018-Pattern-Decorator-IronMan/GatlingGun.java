// ConcreteDecorator 클래스 -> 무기 종류1
public class GatlingGun extends WeaponsDecorator {
	
	private Suit suit;
	public GatlingGun(Suit suit) {
		this.suit = suit;
	}
	
	@Override
	public String getArmor() {
		return suit.getArmor();
	}
	
	@Override
	public String getWeapons() {
		return suit.getWeapons() + " + GatilngGun";
	}
	
	@Override
	public int getAttackPoint() {
		return suit.getAttackPoint() + 500;
	}
}
