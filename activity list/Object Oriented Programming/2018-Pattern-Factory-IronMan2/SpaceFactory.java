
public class SpaceFactory implements SuitAbstractFactory {
	
	@Override
	public Suit createSuit() {
		return new SpaceSuit();
	}

}
