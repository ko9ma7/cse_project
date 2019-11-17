// 6. 최종 결과
/*
 *  자바 API 파일 I/O와 매우 밀접하다
 *  ex) BufferedReader br = new BufferedReader(new FileReader(new File("test.txt")));
 */
public class CookieTest {

	public static void main(String[] args) {

		// 그냥 용감한 쿠키
		Cookie braveCookie = new BraveCookie();
		
		// 우유를 얹은 용감한 쿠키
		Cookie milkBraveCookie = new MilkTopping(braveCookie);
		
		// 그 위에 초콜릿을 얹은 용감한 쿠키
		Cookie chocolateMilkBraveCookie = new ChocolateTopping(milkBraveCookie);
		
		// 그 위에 우유를 한 번 더 넣은 용감한 쿠키
		Cookie chocolateDoubleMilkBraveCookie = new MilkTopping(chocolateMilkBraveCookie);
		
		System.out.println(chocolateDoubleMilkBraveCookie.getName());
		
		// 소다 쿠키 만들기
		Cookie sodaCookie = new Cookie() {
			
			@Override
			public String getName() {
				return "소다 쿠키";
			}
			
		};
		
		// 초콜릿을 두 번 넣은 소다 쿠키
		sodaCookie = new ChocolateTopping(new ChocolateTopping(sodaCookie));

		System.out.println(sodaCookie.getName());
		
	}

}
