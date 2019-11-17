
public class Test {
	public static void test(InternetShoppingOrder order) {
		order.processOrder();
	}
	public static void main(String[] args) { 
		test(new CreditCardPaymentOrder());
		test(new BankTransferPaymentOrder());
	}
}
