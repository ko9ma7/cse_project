import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴을 사용하지 않고 커피 첨가물 추가에 따른 가격 계산
 * 실습 2
 * Beverage
 * @author 김상진
 *
 */
public abstract class Beverage{
	private String description = "Unknown Beverage";
	private ArrayList<Condiment> condiments = new ArrayList<>(); // 첨가물을 유지할 수 있는 멤버 리스트
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){ // 첨가물 출력
		
		String resultDescription = description;

		Iterator<Condiment> itr = condiments.iterator();
		
		while (itr.hasNext()) {
			resultDescription = resultDescription + ", " + itr.next().getDescription();
		}
	
		return resultDescription;
		
//		return description + ", " + condiments.stream().map(c->c.getDescription()).sorted().collect(Collectors.joining(" ")); // 교수님 방법
	}
	public void addCondiment(Condiment condiment){ // 첨가물 추가 메소드
		condiments.add(condiment);
	}
	public int cost(){ // 커피 가격 출력
	
		int resultCost = 0;
		Iterator<Condiment> itr = condiments.iterator();
		
		while (itr.hasNext()) {
			resultCost += itr.next().cost();
		}
		
		return resultCost;
		
	}
}