import java.util.ArrayList;
import java.util.List;

public class ReportTest {

	public static void main(String[] args) {
		
		Customer cus1 = new Customer("허 준", 100);
		Customer cus2 = new Customer("김유신", 150);
		Customer cus3 = new Customer("장보고", 250);
		Customer cus4 = new Customer("이순신", 300);
		Customer cus5 = new Customer("세종대왕", 1000);

		List<Customer> customers = new ArrayList<>();
		customers.add(cus1);
		customers.add(cus2);
		customers.add(cus3);
		customers.add(cus4);
		customers.add(cus5);
		
		ReportGenerator simpleReport = new SimpleReportGenerator(customers);
		ReportGenerator complexReport = new ComplexReportGenerator(customers);
		
		System.out.println("****단순 보고서 작성****");
		System.out.println(simpleReport.generate());
		System.out.println("****복잡 보고서 작성****");
		System.out.println(complexReport.generate());
		
	}

}
