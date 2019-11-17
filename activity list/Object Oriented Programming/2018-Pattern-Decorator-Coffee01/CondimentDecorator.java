// 이 클래스를 만드는 이점: Beverage의 타입을 구분 혹은 집합 시켜줌

public abstract class CondimentDecorator extends Beverage { // 장식을 Beverage로 사용할 수 있어야 하므로 상속
	public abstract String getDescription(); // 원래는 Beverage 클래스에서 abstract가 아님 -> 중간에(상속받은 CondimentDecorator 클래스) abstract로 바꿈 
											 // -> 마지막 하위 객체들은 이 메소드를 구현해야 함
}
