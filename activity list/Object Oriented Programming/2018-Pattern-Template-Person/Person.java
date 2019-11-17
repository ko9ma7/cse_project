
// 전사, 궁수, 마법사가 같이 사용할 수 있도록 공통의 추상 클래스 구현
public abstract class Person {
	
	// 전투를 준비합니다.
	// 알고리즘 틀 생성
	final void readyToBattle() {
		startBodyTraining();
		prepareWeapon();
		prepareArmor();
		if (isUsingPrepareOther()) {
			prepareOther();
		}
	}
	
	// 상속 받은 클래스에서 수정할 수 없도록 final 키워드를 사용
	final void startBodyTraining() {
		System.out.println("체력을 단련합니다.");
	}
	
	// 무기를 손질할 때 사용합니다.
	abstract void prepareWeapon();
	
	// 갑옷을 착용합니다.
	abstract void prepareArmor();
	
	// 다른 무언가를 쓰려고 하는 선택적 메소드인 "Hook"
	boolean isUsingPrepareOther() {
		return false;
	}
	
	void prepareOther() {}
	
}
