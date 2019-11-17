/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 기본 장식 패턴 예제
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		
		Beverage beverage1 = new HouseBlend(); 
		beverage1 = new Mocha(beverage1);
		beverage1 = new Whip(beverage1);
		beverage1 = new Mocha(beverage1);
		System.out.printf("1. %s: %,d원%n", beverage1.getDescription(), beverage1.cost());
		
		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2);
		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
		System.out.printf("2. %s: %,d원%n", beverage2.getDescription(), beverage2.cost());
		
		Beverage beverage3 = new Decaf();
		beverage3 = new Soy(beverage3);
		beverage3 = new Milk(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.printf("3. %s: %,d원%n", beverage3.getDescription(), beverage3.cost());
		
		Beverage beverage4 = new Expresso();
		beverage4 = new Soy(beverage4);
		beverage4 = new Milk(beverage4);
		beverage4 = new Whip(beverage4);
		System.out.printf("4. %s: %,d원%n", beverage4.getDescription(), beverage4.cost());
		
		Beverage beverage5 = new Decaf();
		beverage5 = new Whip(beverage5);
		beverage5 = new Soy(beverage5);
		beverage5 = new Milk(beverage5);
		System.out.printf("5. %s: %,d원%n", beverage5.getDescription(), beverage5.cost());
		
		
		System.out.println();
		System.out.print("beverage1 is equal beverage2?: "); beverage1.equals(beverage1, beverage2); System.out.println();
		System.out.print("beverage1 is equal beverage3?: "); beverage1.equals(beverage1, beverage3); System.out.println();
		System.out.print("beverage2 is equal beverage3?: "); beverage2.equals(beverage2, beverage3); System.out.println();
		System.out.print("beverage3 is equal beverage4?: "); beverage3.equals(beverage3, beverage4); System.out.println();
		System.out.print("beverage3 is equal beverage5?: "); beverage3.equals(beverage3, beverage5); System.out.println();
		System.out.print("beverage4 is equal beverage5?: "); beverage4.equals(beverage4, beverage5); System.out.println();
		
	}
}
