import java.util.ArrayList;

/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 *
 */
// Replace Temp with Query
public class Customer {
	private String name;
	private ArrayList<Rental> rentals = new ArrayList<>();
	public Customer(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void addRental(Rental rental){
		rentals.add(rental);
	}
	public String statement(){
		int totalprice = 0;
		int frequentRenterPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			//Replace Temp with Query -> 임시변수는 함수로 대체한다
			//int thisprice = rental.getCharge();
			frequentRenterPoints += rental.getFrequentRentalPoints();
			result += String.format("\t%s\t%,d원\n", 
					rental.getMovie().getTitle(), rental.getCharge());
			totalprice += rental.getCharge();
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRenterPoints);
		return result;
	}
}
