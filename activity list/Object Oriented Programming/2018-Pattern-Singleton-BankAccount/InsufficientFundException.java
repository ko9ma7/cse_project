
public class InsufficientFundException extends RuntimeException {
	public InsufficientFundException() {
		super("잔액부족");
	}
	public InsufficientFundException(String message) {
		super(message);
	}
}
