/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 기본 장식 패턴 예제
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend(); // 처음 음료를 HouseBlend로 (cost : 1000) 
		beverage = new Mocha(beverage); // 첫 번째 포장 Mocha (cost : 1200) 
		beverage = new Whip(beverage); // 두 번째 포장 Whip (cost : 1700)
		beverage = new Mocha(beverage); // 세 번째 포장 Mocha (cost : 1900)
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
	}
}
