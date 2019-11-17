
public class HydroFactory implements SuitAbstractFactory {

	@Override
	public Suit createSuit() {
		return new HydroSuit();
	}
	
}
