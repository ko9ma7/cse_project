// 이동과 공격을 갖춘 레비테이션 망토
public class Item {

	Move move;
	Attack attack;
	
	public Item(String name, Move move, Attack attack) {
		System.out.println(name + " 착용");
		this.move = move;
		this.attack = attack;
	}

	public void doMove() { move.doMove(); }
	public void doAttack() { attack.doAttack(); }
	
}
