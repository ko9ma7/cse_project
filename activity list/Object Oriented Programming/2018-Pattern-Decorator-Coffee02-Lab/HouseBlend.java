
public class HouseBlend extends Beverage {
	public HouseBlend(){
		setDescription("Houseblend Coffee");
	}
	@Override
	public int cost() {
		return super.cost()+1000;
	}
}
