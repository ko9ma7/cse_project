// 5. 초코맛 토핑 추가된 쿠키
/* Chocolate Topping */
public class ChocolateTopping extends Topping {

	public ChocolateTopping(Cookie cookie) {
		super(cookie);
	}
	
	@Override
	public String getName() {
		return "초콜릿맛 " + cookie.getName(); 
	}
	
}
