/**
 * 2018년도 2학기 객체지향개발론및실습
 * 장식패턴
 * Beverage
 * 기본 예제
 * @author 김상진
 *
 */
/*
 *  추상 클래스 사용 목적
 *  
 *  추상 클래스는 슈퍼 클래스를 상속받아서 기능을 이용하고, 확장시킨다.
 *  다중 상속의 모호성 때문에 하나만 상속받을 수 있다.
 *  
 *  추상 클래스 특징
 *  1. 추상 클래스를 상속받는 클래스는 반드시 추상 메소드를 구현해야 한다.
 *  2. 단일 상속이다.
 *  3. 이러이러한 메소드가 있지만 가져다 쓰거나 오버라이드 하거나, abstract가 붙은 메소드는 반드시 구현해야 한다.
 *  4. 사용 개념
 *     ex) 어미고양이(부모 클래스) - 야생고양이
 *           -자는법(메소드)
 *           -집에서 사는법(추상화 메소드)
 *         
 *         새끼고양이(자식 클래스) - 집고양이
 *           -자는법(메소드)
 *           -집에서 사는법(메소드)
 *           
 *         -> 어미고양이는 자는법이 있었고, 자식에게 전수하였지만, 새끼고양이는 집고양이라 어미고양이와 자는법이 다르다.
 *         -> 새끼고양이가 나름대로 자는법을 새로 터득(부모 클래스의 내용을 Override 하는 것)
 *         -> 새끼고양이는 집생활을 하며 집에서 사는법을 터득(부모 클래스보다 더 많이 구현 - 기능 추가)
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
