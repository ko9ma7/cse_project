// Component 클래스 -> 슈트 종류 가져오기
public abstract class Suit{

	protected String armor = "IronMan";
	protected String weapons = "RepulsorBeam";
	protected int attackPoint = 0;
	
	public String getArmor() {
		return this.armor;
	}
	
	public String getWeapons() {
		return this.weapons;
	}
	
	public abstract int getAttackPoint();
	
	public void printStat() {
		System.out.println("armor: " + this.getArmor());
		System.out.println("weapons: " + this.getWeapons());
		System.out.println("attack: " + this.getAttackPoint());
	}
	
}
