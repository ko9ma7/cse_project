import java.util.List;

// 고객 점수가 200점 이상인 고객 대상으로 보고서 생성하는 클래스
public class ComplexReportGenerator extends ReportGenerator {

	public ComplexReportGenerator(List<Customer> customers) {
		super(customers);
	}

	// complexReport에서는 고객의 포인트가 200점 이상인 경우만 출력
	String complexReport = customers.stream()
			.filter(c -> c.getPoint() >= 200)
			.map(c->String.format("%s : %d\n", c.getName(), c.getPoint()))
			.reduce(report, String::concat);
	
	@Override
	public String generate() { return complexReport; }
	
}
