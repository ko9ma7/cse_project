/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 장식 패턴를 위한 팩토리 메소드 예제
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		try {
			Beverage beverage 
				= Beverage.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha");
			System.out.printf("%s: %,d원%n", 
				beverage.getDescription(), beverage.cost());
			beverage 
				= Beverage.createCoffee("Decaf", "Soy", "Whip");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
