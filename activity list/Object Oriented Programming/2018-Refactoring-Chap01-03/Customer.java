import java.util.ArrayList;

/**
 * 2018년도 2학기 객체지향개발론및실습 
 * 리펙토링
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * @author 김상진 
 *
 */
// Move Method
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
			int thisprice = rental.getCharge();
			frequentRenterPoints += 100;
			if((rental.getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
				rental.getDaysRented()>1)
				frequentRenterPoints += 100;
			result += String.format("\t%s\t%,d원\n", rental.getMovie().getTitle(), thisprice);
			totalprice += thisprice;
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRenterPoints);
		return result;
	}
	// thisprice가 두 군데에서 사용하고 있음 -> 가급적이면 불필요한 임시변수는 함수호출로 대체한다(Chap01-04)
	
	
	/*
	// Move Method
	// Customer 클래스의 상태를 사용하지 않음 -> 굳이 Customer 클래스에 있을 이유가 없다 -> Rental 클래스로 옮김
	private int rentalPrice(Rental rental){
		int price = 0;
		switch(rental.getMovie().getPriceCode()){
		case REGULAR:
			price += 2000;
			if(rental.getDaysRented()>2)
				price += (rental.getDaysRented()-2)*1500;
			break;
		case NEW_RELEASE:
			price += rental.getDaysRented()*2000;
			break;
		case CHILDRENS:
			price += 1500;
			if(rental.getDaysRented()>3)
				price += (rental.getDaysRented()-3)*1500;
			break;
		}
		return price;
	}
	*/
}
