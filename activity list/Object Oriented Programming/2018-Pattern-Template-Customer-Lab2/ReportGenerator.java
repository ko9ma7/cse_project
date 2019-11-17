import java.util.ArrayList;
import java.util.List;

public abstract class ReportGenerator {
	
	List<Customer> customers = new ArrayList<>();
	String report;
	
	public ReportGenerator(List<Customer> customers) { // 생성자에서 고객 리스트의 값을 받아오고
		this.customers = customers; 
		report = String.format("고객 수: %d명\n", customers.size()); // 고객의 수를 지정해준다
	}
	
	public abstract String generate(); // 서브 클래스에서 반드시 구상해주어야 할 메소드
	
}
