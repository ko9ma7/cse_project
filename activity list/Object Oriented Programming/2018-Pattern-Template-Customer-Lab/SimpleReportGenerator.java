import java.util.List;

// 고객 정보 출력 클래스
public class SimpleReportGenerator {

	public String generate(List<Customer> customers) {
		String report = String.format("고객 수: %d명\n", customers.size());
		report = customers.stream().map(c->String.format("%s : %d\n", c.getName(), c.getPoint())).reduce(report, String::concat);
		
		return report;
	}
	
}
