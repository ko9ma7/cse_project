import java.util.Random;

// 컴퓨터가 쓰는 전략
public class LastHandBasedStrategy implements PlayingStrategy {
	
	private Random rand = new Random();
	private HandType prevHand;
	private int result;
	
	@Override
	public HandType nextHand() { // 다음에 낼 동작
		
		if (prevHand == null) { // 이전 값이 없다면
			return HandType.valueOf(rand.nextInt(3)); // 0(묵), 1(찌), 2(빠) 중에 랜덤으로 선택
		}
		
		int[] array;
		
		if (prevHand == HandType.valueOf(0)) {
			
			array = new int[] { 0, 0, 1, 1, 1, 1, 2, 2, 2, 2 };
			
			System.out.println("묵은 안낼거야!");
					
		} else if (prevHand == HandType.valueOf(1)) {
			
			array = new int[] { 0, 0, 0, 0, 1, 1, 2, 2, 2, 2 };
			
			System.out.println("찌는 안낼거야!");
			
		} else {
			
			array = new int[] { 0, 0, 0, 0, 1, 1, 1, 1, 2, 2 };
			
			System.out.println("빠는 안낼거야!");
			
		}
		
		return HandType.valueOf(array[rand.nextInt(10)]);
	}

	@Override
	public void setLastUserHand(HandType hand) {
		prevHand = hand; // 이전의 동작을 prevHand에 넣어줌
		System.out.println("이전에 냈던 손이다 임마!\n");
	}

	@Override
	public void setLastWinComputerHand(HandType computerHand) {}

	@Override
	public void setLastLoseComputerHand(HandType computerHand) {}

	@Override
	public void setAnalyzePattern(HandType userHand, HandType computerHand) {}

}
