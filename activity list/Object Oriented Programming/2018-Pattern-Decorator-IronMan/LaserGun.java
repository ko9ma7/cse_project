// ConcreteDecorator 클래스 -> 무기 종류3
public class LaserGun extends WeaponsDecorator {

	private Suit suit;
	public LaserGun(Suit suit) {
		this.suit = suit;
	}
	
	@Override
	public String getArmor() {
		return suit.getArmor();
	}
	
	@Override
	public String getWeapons() {
		return suit.getWeapons() + " + LaserGun";
	}
	
	@Override
	public int getAttackPoint() {
		return suit.getAttackPoint() + 500;
	}
	
}
