
public class StealthFactory implements SuitAbstractFactory {

	@Override
	public Suit createSuit() {
		return new StealthSuit();
	}
	
}
