
public class Coffee extends CaffeineBeverage{ // 추상 클래스를 상속
	public void prepareRecipe(){ // 구현
		boilWater();
		brewCoffeeGrinds();
		pourInCup();
		addSugarAndMilk();
	}
	private void brewCoffeeGrinds(){
		System.out.println("커피를 내림");
	}
	private void addSugarAndMilk(){
		System.out.println("밀크와 설탕 추가");
	}
}
