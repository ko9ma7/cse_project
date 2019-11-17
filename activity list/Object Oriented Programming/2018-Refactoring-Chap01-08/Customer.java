import java.util.ArrayList;

/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 *
 */
// 중간에 for문을 삭제하기 위해 람다식 표현 사용
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
	// for문을 삭제하여 String을 사용
	public String statement(){
		String result = String.format("고객 %s님의 대여목록:\n", name);
		result += rentals.stream()
			.map(r->String.format("\t%s\t%,d원\n", r.getMovie().getTitle(), r.getCharge()))
			.reduce("", String::concat);	
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %d점\n", getTotalFrequencyRentalPoints());
		return result;
	}
	private int getTotalCharge(){
		return rentals.stream().map(r->r.getCharge()).reduce(0,Integer::sum);
	}
	private int getTotalFrequencyRentalPoints(){
		return rentals.stream().map(r->r.getFrequentRentalPoints()).reduce(0,Integer::sum);
	}
}
	
