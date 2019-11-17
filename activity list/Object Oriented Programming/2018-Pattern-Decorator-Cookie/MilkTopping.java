// 4. 우유맛 토핑 추가된 쿠키 -> 토핑 클래스를 상속 받아 우유맛 토핑 추가
/* Milk Topping */
public class MilkTopping extends Topping {

	public MilkTopping(Cookie cookie) {
			super(cookie);
	}
	
	@Override
	public String getName() {
		return "우유맛 " + cookie.getName();
	}
	
}
