import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RentalSystemTest {
	@Test
	void test() {
		Customer sangjin = new Customer("김상진");
		sangjin.addRental(new Rental(new Movie("미션임파서블: 풀아웃", PriceCode.REGULAR),2));
		sangjin.addRental(new Rental(new Movie("몬스터호텔3", PriceCode.CHILDRENS),2));
		sangjin.addRental(new Rental(new Movie("협상", PriceCode.NEW_RELEASE),2));
		String expectedResult = 
			"고객 김상진님의 대여목록:\n"+
			"\t미션임파서블: 풀아웃\t2,000원\n" +
			"\t몬스터호텔3\t1,500원\n" +
			"\t협상\t4,000원\n" +
			"총금액: 7,500원\n" +
			"적립포인트: 400점\n";
		assertEquals(sangjin.statement(), expectedResult);
	}
}
