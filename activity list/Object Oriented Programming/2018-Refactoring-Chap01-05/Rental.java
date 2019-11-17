/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Rental 클래스: 대여정보 
 * @author 김상진 
 *
 */
public class Rental {
	private Movie movie;
	private int daysRented;
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
	// 현재 리팩토링 상태는 Customer -> Rental 호출, Rental -> Movie 호출
	public int getCharge(){
		return movie.getCharge(daysRented);
	}
	public int getFrequentRentalPoints(){
		return movie.getFrequentRentalPoints(daysRented);
	}
	/*
	public int getCharge(){
		int price = 0;
		switch(getMovie().getPriceCode()){
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
	public int getFrequentRentalPoints(){
		if((getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
			getDaysRented()>1)
			return 200;
		else return 100;
	}
	*/
}
