import java.util.ArrayList;

/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 *
 */
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
		int totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			int thisAmount = 0;
			switch(rental.getMovie().getPriceCode()){
			case REGULAR:
				thisAmount += 2000;
				if(rental.getDaysRented()>2)
					thisAmount += (rental.getDaysRented()-2)*1500;
				break;
			case NEW_RELEASE:
				thisAmount += rental.getDaysRented()*2000;
				break;
			case CHILDRENS:
				thisAmount += 1500;
				if(rental.getDaysRented()>3)
					thisAmount += (rental.getDaysRented()-3)*1500;
				break;
			}
			frequentRenterPoints += 100;
			if((rental.getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
				rental.getDaysRented()>1)
				frequentRenterPoints += 100;
			result += String.format("\t%s\t%,d원\n", rental.getMovie().getTitle(), thisAmount);
			totalAmount += thisAmount;
		}
		result += String.format("총금액: %,d원\n", totalAmount);
		result += String.format("적립포인트: %d점\n", frequentRenterPoints);
		return result;
	}
}