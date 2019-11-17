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
}
