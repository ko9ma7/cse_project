/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴
 * Beverage
 * 기본 예제
 * @author 김상진
 *
 */

// 실습1-1 - 추가된 책임을 제거하는 방법(Beverage 클래스에는 메소드 추가 x)
public abstract class Beverage{
	private String description = "Unknown Beverage";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	
}
