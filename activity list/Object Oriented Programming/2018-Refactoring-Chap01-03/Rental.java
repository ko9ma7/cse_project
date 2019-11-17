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
	// MOVED HERE
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
}
