// 실습4. 장식 제한하기
public abstract class Beverage{
	private String description = "Unknown Beverage";
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public abstract int cost();
	protected abstract Beverage getNext();
	protected boolean doubleAllowed() {
		return false;
	}
}
