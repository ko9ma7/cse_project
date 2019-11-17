
public class TemplateMain {

	public static void main(String[] args) {
		
		// 전사는 전투 준비를 위해서 체력을 단련하고, 검을 닦고, 갑옷을 착용합니다.
		Warrior warrior = new Warrior();
		warrior.readyToBattle();
		
		// 궁사는 전투 준비를 위해서 체력을 단련하고, 활을 손질하고, 화샅통을 챙기고, 화살을 준비합니다.
		Archer archer = new Archer();
		archer.readyToBattle();
		
		// 마법사는 전투 준비를 위해서 체력을 단련하고, 지팡이를 준비하고, 로브를 착용합니다.
		Wizard wizard = new Wizard();
		wizard.readyToBattle();
		
	}

}
