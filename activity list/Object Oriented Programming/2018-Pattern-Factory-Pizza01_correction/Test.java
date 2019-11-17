
public class Test {
	public static void main(String[] args) {
		PizzaStore pizzaStore = new PizzaStore(new SimplePizzaFactory());
		pizzaStore.orderPizza("치즈");
		pizzaStore.orderPizza("포테이토");
		pizzaStore.orderPizza("고르곤졸라");
	}
}
