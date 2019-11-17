/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 장식 패턴 대신에 변형된 전략 패턴 사용
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();
		beverage.addCondiment(new Mocha());
		beverage.addCondiment(new Whip());
		beverage.addCondiment(new Mocha());
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
	}
}
