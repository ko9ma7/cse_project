// test
public class IronManDispatch {

	public static void main(String[] args) {
		
		// 슈트 Mark1 착용
		Suit mark1 = new Mark1();

		// 무기 MachineGun 장착
		mark1 = new MachineGun(mark1);
		
		// 무기 GatlingGun 장착
		mark1 = new GatlingGun(mark1);
		
		// 현재 Mark1의 상태 출력
		mark1.printStat();
		
		/////////// *** Change Suit *** ///////////
		System.out.println("\nChange Suit!!\n");
		
		// 슈트 Mark2 착용
		Suit mark2 = new Mark2();
		
		// 무기 LaserGun 장착
		mark2 = new LaserGun(mark2);
		
		// 현재 Mark2의 상태 출력
		mark2.printStat();
		
	}
}
