
public class ObjectCoffeeAdapter implements Coffee {
	private BlackTea tea;
	public ObjectCoffeeAdapter(BlackTea tea) { this.tea = tea; }
	@Override
	public void boilWater() { tea.boilWater(); }
	@Override
	public void brewCoffeeGrinds() { tea.steepTeaBag(); }
}
