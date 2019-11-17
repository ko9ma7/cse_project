
public class SimplePizzaFactory implements PizzaFactory {

	public Pizza createPizza(String type) {
		
		Pizza pizza = null;
		switch(type){
		case "치즈": pizza = new CheesePizza(); break;
		case "포테이토": pizza = new PotatoPizza(); break;
		case "고르곤졸라": pizza = new GorgonzolaPizza(); break;
		} // 마르게리타, 하와이안, 고르곤졸라, 페퍼로니, 불고기
		return pizza;
		
	}
	
}


