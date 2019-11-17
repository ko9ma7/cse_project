
public class Tea extends CaffeineBeverage{
	public void prepareRecipe(){ // 구현
		boilWater();
		steepTeaBag();
		pourInCup();
		addLemon();
	}
	private void steepTeaBag(){
		System.out.println("티백을 담그다");
	}
	private void addLemon(){
		System.out.println("레몬 추가");
	}
}
