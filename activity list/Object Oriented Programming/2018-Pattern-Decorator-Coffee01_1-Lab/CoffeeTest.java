/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴: Coffee
 * 기본 장식 패턴 + CondimentDecorator가 장식대상 유지
 * @author 김상진
 *
 */
public class CoffeeTest {
	public static void main(String[] args) {
		
		System.out.println("*** CondimentDecorator에만 removeCondiment 메소드 추가 ***\n");
		
		Beverage beverage = new HouseBlend();
		beverage = new Mocha(beverage);
		beverage = new Whip(beverage);
		beverage = new Mocha(beverage);
		System.out.printf("%s: %,d원%n", 
			beverage.getDescription(), beverage.cost());
		
		if (beverage instanceof CondimentDecorator) { // 참조변수가 참조하고 있는 인스턴스의 실제 타입, 왼쪽에는 참조변수, 오른쪽에는 타입
													  // true가 나오면 참조변수가 검사한 타입으로 형변환이 가능하다는 뜻
			beverage = ((CondimentDecorator)beverage).removeCondiment();
		}
		
		System.out.printf("%s: %,d원%n",
			beverage.getDescription(), beverage.cost());
	}
}
