
public abstract class CaffeineBeverage {
	public final void prepareRecipe(){ // 틀을 정해주었으므로 final로 설정
		boilWater();
		brew();
		pourInCup();
		if(customerWantsCondiments()) 
			addCondiment();
	}
	private void boilWater(){
		System.out.println("물을 끓임");
	}
	protected abstract void brew();
	private void pourInCup(){
		System.out.println("컵에 따르다");
	}
	protected abstract void addCondiment();
	protected boolean customerWantsCondiments(){ // Hook - 선택적 사용
		return true;
	}
}
