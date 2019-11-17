import java.util.List;

// 고객 점수가 200점 이상인 고객 대상으로 보고서 생성하는 클래스
public class ComplexReportGenerator {

	public String generate(List<Customer> customers) {
		String report = String.format("고객 수 : %d명\n", customers.size());
		report = customers.stream()
				.filter(c -> c.getPoint() >= 200)
				.map(c->String.format("%s : %d\n", c.getName(), c.getPoint()))
				.reduce(report, String::concat);
		
		return report;
	}
	
}
