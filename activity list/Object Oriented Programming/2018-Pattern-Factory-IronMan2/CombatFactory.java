
public class CombatFactory implements SuitAbstractFactory {

	@Override
	public Suit createSuit() {
		return new CombatSuit();
	}
	
}
