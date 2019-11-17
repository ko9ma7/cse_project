/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 장식 패턴: 장식 제한 (팩토리 메소드를 이용한 특정 장식자 다중 장식 불가)
 * 실습 4
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		try {
			Beverage beverage = Beverage.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha");;
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
			beverage = Beverage.createCoffee("HouseBlend", "Soy", "Whip", "Soy");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
