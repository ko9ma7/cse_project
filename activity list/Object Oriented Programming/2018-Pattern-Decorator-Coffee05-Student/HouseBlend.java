
public class HouseBlend extends Beverage {
	
	public HouseBlend(){
		setDescription("Houseblend Coffee");
	}
	
	@Override
	public int cost() { // 천원이라는 가격 모델링을 함 -> 데이터 같은 메소드
		return 1000;
	}

	@Override
	protected Beverage getBeverage() {
		// TODO Auto-generated method stub
		return null;
	}
	
}