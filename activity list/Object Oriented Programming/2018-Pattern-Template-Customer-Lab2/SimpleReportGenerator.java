import java.util.List;

// 고객 정보 출력 클래스
public class SimpleReportGenerator extends ReportGenerator {

	public SimpleReportGenerator(List<Customer> customers) {
		super(customers);
	}

	// simpleReport에서는 고객의 모든 정보를 출력
	String simpleReport = customers.stream().map(c->String.format("%s : %d\n", c.getName(), c.getPoint())).reduce(report, String::concat);
	
	@Override
	public String generate() { return simpleReport; }
	
}
