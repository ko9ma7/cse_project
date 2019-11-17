
public class PizzaStore {
	public Pizza orderPizza(String type){
		Pizza pizza = null;
		switch(type){
		case "치즈": pizza = new CheesePizza(); break;
		case "포테이토": pizza = new PotatoPizza(); break;
		} // 마르게리타, 하와이안, 고르곤졸라, 페퍼로니, 불고기
		pizza.prepare(); 
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza; 
	}
}
