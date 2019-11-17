
public class ClassCoffeeAdapter extends BlackTea implements Coffee {
	@Override
	public void boilWater() { super.boilWater(); }
	@Override
	public void brewCoffeeGrinds() { steepTeaBag(); }
}