// ConcreteDecorator 클래스 -> 무기 종류2
public class MachineGun extends WeaponsDecorator{

	private Suit suit;
	public MachineGun(Suit suit) {
		this.suit = suit;
	}
	
	@Override
	public String getArmor() {
		return suit.getArmor();
	}
	
	@Override
	public String getWeapons() {
		return suit.getWeapons() + " + MachineGun";
	}
	
	@Override
	public int getAttackPoint() {
		return suit.getAttackPoint() + 800;
	}
	
}
