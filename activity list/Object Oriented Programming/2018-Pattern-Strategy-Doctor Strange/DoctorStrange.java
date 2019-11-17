
public class DoctorStrange {

	public static void main(String[] args) {
		
		Item item = new Item("레비테이션 망토", new Fly(), new SpaceDistortion());
		item.doMove();
		item.doAttack();
		
		item = new Item("아가모토의 눈", new Fly(), new EyeOfAgamoto());
		item.doMove();
		item.doAttack();
		
		item = new Item("슬링거", new Teleportation(), new Seraphim_Shield());
		item.doMove();
		item.doAttack();
		
	}

}
