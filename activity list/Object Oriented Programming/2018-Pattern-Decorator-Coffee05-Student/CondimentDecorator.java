
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public abstract class CondimentDecorator extends Beverage { 
	
	private Beverage beverage; // CondimentDecorator만의 멤버 변수로 선언
	protected void setDecoratee(Beverage beverage) { this.beverage = beverage; }
	@Override
	protected Beverage getBeverage() { return beverage; }
	public abstract String getDescription(); 
	
	
	// temperatureLog.stream().max(Comparator.comparing(Float::valueOf)).get()); 이런식으로 생각해보기!!
	
	@Override
	public boolean equals(Beverage standard, Beverage compare) { // 장식된 첨가물의 동일 여부
		
		/*
		 *  1. 처음 클래스가 같은지 다른지 비교한다.
		 *  2. 나머지 클래스부터는 첨가물의 종류에 따라 cnt를 주어 개수를 구한다.(순서에 상관없으므로)
		 *  3. 마지막에 커피 종류를 비교할 수 있도록 부모 클래스의 equals를 호출한다.
		 */
		
		// Step 1. 필요한 변수 선언
		Beverage standardCurrentBeverage;
		Beverage compareCurrentBeverage;
		int standardMochaCnt = 0, standardWhipCnt = 0, standardMilkCnt = 0, standardSoyCnt = 0; // 첨가물은 순서가 중요하지 않기 때문에 포함된 개수를 비교하기 위해 각각 count를 주었다.
		int compareMochaCnt = 0, compareWhipCnt = 0, compareMilkCnt = 0, compareSoyCnt = 0;
		boolean isEqualBeverage = false;

		// Step 2. 처음 standard와 compare의 클래스 비교(이 equals문으로 들어왔다면, 첨가물이 포함되어 있다는 것이 명세)
		if (standard.getClass() == compare.getClass()) { // 처음 장식된 첨가물이 같다. 하지만 더 안쪽의 장식을 비교해보자.
			
			if (standard.getClass() == Mocha.class) { standardMochaCnt++; compareMochaCnt++; }
			else if (standard.getClass() == Whip.class) { standardWhipCnt++; compareWhipCnt++; }
			else if (standard.getClass() == Milk.class) { standardMilkCnt++; compareMilkCnt++; }
			else if (standard.getClass() == Soy.class) { standardSoyCnt++; compareSoyCnt++; } 
			
			// check condimentDecorator of standard beverage
			standardCurrentBeverage = standard.getBeverage();
			while (true) {
				
				if (standardCurrentBeverage.getClass() == HouseBlend.class || standardCurrentBeverage.getClass() == DarkRoast.class 
						|| standardCurrentBeverage.getClass() == Decaf.class || standardCurrentBeverage.getClass() == Expresso.class) break;
				
				if (standardCurrentBeverage.getClass() == Mocha.class) standardMochaCnt++;
				else if (standardCurrentBeverage.getClass() == Whip.class) standardWhipCnt++;
				else if (standardCurrentBeverage.getClass() == Milk.class) standardMilkCnt++;
				else if (standardCurrentBeverage.getClass() == Soy.class) standardSoyCnt++;
				
				standardCurrentBeverage = standardCurrentBeverage.getBeverage();
				
			}
			
			// check condimentDecorator of compare beverage
			compareCurrentBeverage = compare.getBeverage();
			while (true) {
				
				if (compareCurrentBeverage.getClass() == HouseBlend.class || compareCurrentBeverage.getClass() == DarkRoast.class 
						|| compareCurrentBeverage.getClass() == Decaf.class || compareCurrentBeverage.getClass() == Expresso.class) break;
				
				if (compareCurrentBeverage.getClass() == Mocha.class) compareMochaCnt++;
				else if (compareCurrentBeverage.getClass() == Whip.class) compareWhipCnt++;
				else if (compareCurrentBeverage.getClass() == Milk.class) compareMilkCnt++;
				else if (compareCurrentBeverage.getClass() == Soy.class) compareSoyCnt++;
				
				compareCurrentBeverage = compareCurrentBeverage.getBeverage();
				
			}
			
			// 마지막에 동일 커피 사용 유무 확인
			if (super.equals(standardCurrentBeverage, compareCurrentBeverage)) isEqualBeverage = true;
			
			if (isEqualBeverage == true && standardMochaCnt == compareMochaCnt && standardWhipCnt == compareWhipCnt && standardMilkCnt == compareMilkCnt
					&& standardSoyCnt == compareSoyCnt) System.out.println("equal Decorated Beverage!");
			else System.out.println("not equal Decorated Beverage!");
		
		} 
		
		else { // 처음 장식된 첨가물이 다르다. 하지만 더 안쪽의 장식을 비교해보자.
			
			if (standard.getClass() == Mocha.class) standardMochaCnt++;
			else if (standard.getClass() == Whip.class) standardWhipCnt++;
			else if (standard.getClass() == Milk.class) standardMilkCnt++;
			else if (standard.getClass() == Soy.class) standardSoyCnt++; 
			
			if (compare.getClass() == Mocha.class) compareMochaCnt++;
			else if (compare.getClass() == Whip.class) compareWhipCnt++;
			else if (compare.getClass() == Milk.class) compareMilkCnt++;
			else if (compare.getClass() == Soy.class) compareSoyCnt++; 
			
			
			// check condimentDecorator of standard beverage
			standardCurrentBeverage = standard.getBeverage();
			while (true) {
				
				if (standardCurrentBeverage.getClass() == HouseBlend.class || standardCurrentBeverage.getClass() == DarkRoast.class 
						|| standardCurrentBeverage.getClass() == Decaf.class || standardCurrentBeverage.getClass() == Expresso.class) break;
				
				if (standardCurrentBeverage.getClass() == Mocha.class) standardMochaCnt++;
				else if (standardCurrentBeverage.getClass() == Whip.class) standardWhipCnt++;
				else if (standardCurrentBeverage.getClass() == Milk.class) standardMilkCnt++;
				else if (standardCurrentBeverage.getClass() == Soy.class) standardSoyCnt++;
				
				standardCurrentBeverage = standardCurrentBeverage.getBeverage();
				
			}
			
			// check condimentDecorator of compare beverage
			compareCurrentBeverage = compare.getBeverage();
			while (true) {
				
				if (compareCurrentBeverage.getClass() == HouseBlend.class || compareCurrentBeverage.getClass() == DarkRoast.class 
						|| compareCurrentBeverage.getClass() == Decaf.class || compareCurrentBeverage.getClass() == Expresso.class) break;
				
				if (compareCurrentBeverage.getClass() == Mocha.class) compareMochaCnt++;
				else if (compareCurrentBeverage.getClass() == Whip.class) compareWhipCnt++;
				else if (compareCurrentBeverage.getClass() == Milk.class) compareMilkCnt++;
				else if (compareCurrentBeverage.getClass() == Soy.class) compareSoyCnt++;
				
				compareCurrentBeverage = compareCurrentBeverage.getBeverage();
				
			}
			
			// 마지막에 동일 커피 사용 유무 확인
			if (super.equals(standardCurrentBeverage, compareCurrentBeverage)) isEqualBeverage = true;
			
			if (isEqualBeverage == true && standardMochaCnt == compareMochaCnt && standardWhipCnt == compareWhipCnt && standardMilkCnt == compareMilkCnt
					&& standardSoyCnt == compareSoyCnt) System.out.println("equal Decorated Beverage!");
			else System.out.println("not equal Decorated Beverage!");
		
		}
		/*
		System.out.println("SMC: " + standardMochaCnt + ", SWC: " + standardWhipCnt + ", SMC: " + standardMilkCnt + ", SSC: " + standardSoyCnt);
		System.out.println("CMC: " + compareMochaCnt + ", CWC: " + compareWhipCnt + ", CMC: " + compareMilkCnt + ", CSC: " + compareSoyCnt);
		*/
		return true;
	}

}
