/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Movie 클래스: 영화정보
 * 영화분류: 아동, 일반, 최신 
 * @author 김상진 
 *
 */
public class Movie {
	public enum PriceCode {CHILDRENS, REGULAR, NEW_RELEASE};
	private String title;
	private PriceCode priceCode;
	public Movie(String title, PriceCode priceCode) {
		this.title = title;
		setPriceCode(priceCode);
	}
	public String getTitle() {
		return title;
	}
	public PriceCode getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}
	
	// switch문의 기준점이 어디있느냐에 따라 이동할 위치가 바뀜
	// 하지만 아직까진 조건문이 있으므로 객체지향에선 좋지 않음 -> 전략 패턴으로 바꾸자(계산 전략)
	// 가격을 Movie에 has-a 형태로
	public int getCharge(int daysRented){
		int price = 0;
		switch(priceCode){
		case REGULAR:
			price += 2000;
			if(daysRented>2)
				price += (daysRented-2)*1500;
			break;
		case NEW_RELEASE:
			price += daysRented*2000;
			break;
		case CHILDRENS:
			price += 1500;
			if(daysRented>3)
				price += (daysRented-3)*1500;
			break;
		}
		return price;
	}
	public int getFrequentRentalPoints(int daysRented){
		switch(priceCode){
		case NEW_RELEASE:
			if(daysRented>1) return 200;
		default: return 100;
		}
	}
}
