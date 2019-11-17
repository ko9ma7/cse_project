
public abstract class PizzaStore {
	public final Pizza orderPizza(String type){ // final -> 프레임 워크 -> 고정되어 있음
		Pizza pizza = createPizza(type); // 자식에서는 생성부분을 자유롭게 사용가능
		pizza.prepare(); 
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
	protected abstract Pizza createPizza(String type); // PizzaStore에서는 orderPizza만 불러야하므로 createPizza는 필요없음 -> protected로 선언
													   // public으로 선언하면 밖에서도 사용가능해진다
}
