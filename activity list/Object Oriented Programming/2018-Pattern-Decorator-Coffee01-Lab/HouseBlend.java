
public class HouseBlend extends Beverage {
	public HouseBlend(){
		setDescription("Houseblend Coffee");
	}
	@Override
	public int cost() {
		return 1000;
	}
	
	@Override
	public Beverage removeCondiment() {
		// TODO Auto-generated method stub
		return null;
	}
}
