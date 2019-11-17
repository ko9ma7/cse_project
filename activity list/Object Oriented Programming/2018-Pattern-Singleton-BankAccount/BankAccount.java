
public class BankAccount {
	private int balance = 0;
	private int accountNumber;
	
	// private static int numberOfAccounts = 0; // 일반적으로 자바에서 사용하는 방법
	
	public BankAccount(){
		
		// ++numberOfAccounts;
		
		accountNumber //= numberOfAccounts;//
			= AccountNumberGenerator.UNIQUE.getNext(); // 열거형 방식(싱글톤 패턴)
			//= SerialNumberGenerator.getInstance().getNext(); // 메소드 형식으로 변환(싱글톤 패턴) -> 광역적 접근
	} // BankAccount()
	public BankAccount(int amount){
		this();
		deposit(amount);
	} // BankAccount(int)
	public int getBalance(){
		return balance;
	} // getBalance()
	public void deposit(int amount){
		assert(amount>0);
		balance += amount;
	} // deposit(int)
	public void withdraw(int amount){
		assert(amount>0);
		if(amount>0 && balance>=amount) balance -= amount;
		else if(amount>balance) throw new InsufficientFundException(
			String.format("잔액부족> 현재잔액: %d, 인출시도액: %d", balance, amount));
	} // withdraw(int)
	@Override
	public String toString(){
		return String.format("계좌번호: %06d > 잔액: %,d원", accountNumber, balance);
	}
	public static void transfer(BankAccount from, BankAccount to, int amount){
		if(from==null||to==null) return;			
		try{			
			from.withdraw(amount);			
			to.deposit(amount);		
		}		
		catch(InsufficientFundException e){	
			System.out.println(e.getMessage());		
		}	
	} // transfer(BankAccount, BankAccount, int)
}
