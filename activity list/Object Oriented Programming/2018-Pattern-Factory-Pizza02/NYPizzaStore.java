
public class NYPizzaStore extends PizzaStore {
	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		switch(type){
		case "치즈": pizza = new CheesePizza(); break;
		case "포테이토": pizza = new PotatoPizza(); break;
		}
		return pizza;
	}

}
