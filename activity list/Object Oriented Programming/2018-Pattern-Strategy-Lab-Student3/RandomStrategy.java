import java.util.Random;

public class RandomStrategy implements PlayingStrategy {
	
	private Random rand = new Random();
	
	@Override
	public HandType nextHand() {
		return HandType.valueOf(rand.nextInt(3));
	}
	
	@Override
	public void setLastUserHand(HandType hand) {} // 빈 메소드로 만듦

	@Override
	public void setLastWinComputerHand(HandType computerHand) {}

	@Override
	public void setLastLoseComputerHand(HandType computerHand) {}

	@Override
	public void setAnalyzePattern(HandType userHand, HandType computerHand) {}

}