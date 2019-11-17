/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴
 * Beverage
 * 기본 예제
 * @author 김상진
 *
 */

// 실습1 - 추가된 책임을 제거하는 방법
public abstract class Beverage{
	private String description = "Unknown Beverage";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	
	// 현재 클래스의 타입이 Beverage이면 this를 반환, CondimentDecorator 타입이면 장식한 beverage를 반환
	public abstract Beverage removeCondiment();
	
}
