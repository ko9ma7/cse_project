// 3. 쿠키에 얹을 토핑 만들기 - Decorator Class -> 추상 클래스
/* Topping Class */
public abstract class Topping implements Cookie {

	protected Cookie cookie;
	
	public Topping(Cookie cookie) { this.cookie = cookie; }
		
	@Override
	public abstract String getName();

}
