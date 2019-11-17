/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴
 * 장식된 객체의 등가 여부를 비교하는 기능 추가
 * 숙제
 * @author 김상진
 *
 */
public abstract class Beverage{
	private String description = "Unknown Beverage";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	protected abstract Beverage getBeverage();
	// 장식된 객체가 같은지 다른지
	// 가격정보가 멤버로 유지되는 것이 아니라 return문 코드에 유지되고 있다
	// 클래스(getClass())나 클래스 이름(getClass().getName())을 이용하여 비교하자
	// 장식된 순서가 다르더라도 첨가물이 같고, 커피가 같은 종류이면 true를 얻을 수 있어야 한다
	public boolean equals(Beverage beverage1, Beverage beverage2) { // 장식된 커피의 동일 여부
		
		if (beverage1.getClass() == beverage2.getClass()) return true;
		else return false;
		
	}
	
}
