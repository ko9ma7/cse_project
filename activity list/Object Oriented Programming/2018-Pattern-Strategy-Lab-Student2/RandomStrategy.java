import java.util.Random;

public class RandomStrategy implements PlayingStrategy {
	
	private Random rand = new Random();
	@Override
	public HandType nextHand() {
		return HandType.valueOf(rand.nextInt(3));
	}

}