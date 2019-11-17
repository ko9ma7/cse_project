import java.util.ArrayList;

/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 *
 */
// 싱글톤 패턴의 열거형 사용
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
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals)
			result += String.format("\t%s\t%,d원\n", 
					rental.getMovie().getTitle(), rental.getCharge());
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %d점\n", getTotalFrequencyRenterPoints());
		return result;
	}
	private int getTotalCharge() {
		int totalprice = 0;
		for(Rental rental: rentals)
			totalprice += rental.getCharge();
		return totalprice;
	}
	private int getTotalFrequencyRenterPoints() {
		int frequentRenterPoints = 0;
		for(Rental rental: rentals)
			frequentRenterPoints += rental.getFrequentRentalPoints();
		return frequentRenterPoints;
	}
}
