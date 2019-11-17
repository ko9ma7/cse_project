/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 기본 장식 패턴 + CondimentDecorator가 장식대상 유지
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		beverage = new Mocha(beverage);
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
		
		beverage = beverage.removeCondiment();
		System.out.printf("%s: %,d원%n",
			beverage.getDescription(), beverage.cost());
	}
}
