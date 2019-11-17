/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 장식 패턴: 장식 제한 (특정 장식자 다중 장식 불가)
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

		Beverage beverage1 = new HouseBlend();
		beverage1 = new Soy(beverage1);
		beverage1 = new Mocha(beverage1);
		beverage1 = new Soy(beverage1);
		System.out.printf("%s: %,d원%n", 
			beverage1.getDescription(), beverage1.cost());
	}
}
