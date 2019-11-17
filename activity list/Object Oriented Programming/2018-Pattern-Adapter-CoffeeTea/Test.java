
public class Test {
	public static void coffeeTest(Coffee c) {
		c.boilWater();
		c.brewCoffeeGrinds();
	}
	public static void main(String[] args) {
		Coffee coffee = new Latte();
		BlackTea tea = new BlackTea();
		
		Coffee classAdapter = new ClassCoffeeAdapter();
		Coffee objectAdapter = new ObjectCoffeeAdapter(tea);
		
		coffeeTest(objectAdapter);
		
	}

}
