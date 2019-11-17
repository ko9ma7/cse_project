/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴
 * Beverage
 * 장식 제한. Soy만 제한 (한번만 제한)
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
}
