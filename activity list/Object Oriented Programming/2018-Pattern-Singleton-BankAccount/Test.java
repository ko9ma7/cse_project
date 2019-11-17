
public class Test {

	public static void main(String[] args) {
		BankAccount account01 = new BankAccount();
	//	SerialNumberGenerator.getInstance().getNext(); // 어디서든 접근 가능함(광역 접근)
		BankAccount account02 = new BankAccount();
		System.out.println(account01);
		System.out.println(account02);
	}

}
