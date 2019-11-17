import java.util.Random;

public class AttackOrDefenseStrategy implements PlayingStrategy {

	private Random rand = new Random();
	private HandType prevHand;
	private HandType attack;
	private HandType defense;
	
	private HandType analyzeNextHand;
	
	private boolean isWinPlay = false;
	private int result;
	private int count = 0;
	private int[][] analyzeCounting = new int[3][3];
	
	@Override
	public HandType nextHand() {
		
		if (prevHand == null) {
			
			return HandType.valueOf(rand.nextInt(3)); // 0(묵), 1(찌), 2(빠) 중에 랜덤으로 선택
		
		} else {
			
			if (isWinPlay) { // 이전에 냈던 손이 이긴 경우
				
				if (count == 0) {
					
					attackMode(prevHand); // 첫 판에만 모드 사용
					count++;
					
				}
				else { // 나머지 판은 분석모드 사용
					
					performAnalyzePattern();
					count++;
					
				}
				
			} else { // 이전에 냈던 손이 진 경우 
				
				if (count == 0) {
					
					defenseMode(prevHand); // 첫 판에만 모드 사용
					count++;
					
				}
				else { // 나머지 판은 분석모드 사용
					
					performAnalyzePattern();
					count++;
					
				}
				
			} 
			
			return analyzeNextHand;
		} 
		
	}

	@Override
	public void setLastUserHand(HandType hand) { prevHand = hand; }
	
	@Override
	public void setLastWinComputerHand(HandType computerHand) {
		prevHand = computerHand;
		isWinPlay = true;
	}

	@Override
	public void setLastLoseComputerHand(HandType computerHand) {
		prevHand = computerHand;
		isWinPlay = false;
	}
	
	/*
	 *  attackMode인 경우, 상대방이 자신이 냈던 손을 다시 한 번 낼 경우이던가 혹은 둘 다 내지 않았던 손을 낼 것이다.
	 *  따라서 공격자는 상대방의 손을 내던가 둘 다 내지 않았던 손을 낸다.
	 */
	
	public void attackMode(HandType hand) {
		
		int[] array;
		
		if (hand == HandType.valueOf(0)) { // 이긴 손이 주먹(0)일 경우, 상대방은 가위(1)이므로 내야될 손은 가위(1) 혹은 보(2)이다.
			
			array = new int[] { 1, 2 };
			hand = HandType.valueOf(array[rand.nextInt(1)]);
			
		} else if (hand == HandType.valueOf(1)) { // 이긴 손이 가위(1)일 경우, 상대방은 보(2)이므로 내야될 손은 보(2) 혹은 주먹(0)이다.
			
			array = new int[] { 0, 2 };
			hand = HandType.valueOf(array[rand.nextInt(1)]);
			
		} else { // 이긴 손이 보(2)일 경우, 상대방은 주먹(0)이므로 내야될 손은 주먹(0) 혹은 가위(1)이다.
			
			array = new int[] { 0, 1 };
			hand = HandType.valueOf(array[rand.nextInt(1)]);
			
		}
		
		analyzeNextHand = hand;
		
	}
	
	/*
	 *  defenseMode인 경우, 현재 지고있는 상황이기 때문에 매우 불리한 조건에 놓여있다. 따라서 심리상 가위바위보에서는 지더라도 게임에서는 
	 *  지지 않는 현재 내고 있던 손을 다시 한 번 내는 경우가 심리적으로 있다.(자신의 것을 고수하기 위해서) 하지만 유명한 몬티홀 문제를 여기서
	 *  적용한다면, 현재 가위, 바위, 보  중에서 상대방은 가위를 냈고, 나는 현재 보를 냈다. 이때, 다른 선택을 하는 것이 확률적으로 더 높다.
	 *  따라서 지금 내가 내지 않은 경우의 수를 내는 것이 좋다. 그러나 상대방도 자신의 손을 한번 더 낼 수 있는 심리적 요인이 있기 때문에 되도록 
	 *  나머지 두 개의 경우의 수를 각각 1/3과 2/3로 만들어준다.
	 */
	
	public void defenseMode(HandType hand) {
		
		int[] array;
		
		if (hand == HandType.valueOf(0)) { // 진 손이 주먹(0)일 경우, 상대방은 보(2)이므로 내야될 손은 가위(1) 혹은 보(2)이다.
			
			array = new int[] { 1, 1, 2 };
			hand = HandType.valueOf(array[rand.nextInt(2)]);
			
		} else if (hand == HandType.valueOf(1)) { // 진 손이 가위(1)일 경우, 상대방은 주먹(0)이므로 내야될 손은 보(2) 혹은 주먹(0)이다.
			
			array = new int[] { 0, 2, 2 };
			hand = HandType.valueOf(array[rand.nextInt(2)]);
			
		} else { // 진 손이 보(2)일 경우, 상대방은 가위(1)이므로 내야될 손은 주먹(0) 혹은 가위(1)이다.
			
			array = new int[] { 0, 0, 1 };
			hand = HandType.valueOf(array[rand.nextInt(2)]);
			
		}
		
		analyzeNextHand = hand;
		
	}
	
	/*
	 *  총 10번의 시뮬레이션을 돌리면서 상대방(즉, 사용자)의 패턴을 분석하기 위한 메소드이다.
	 *  상대방이 낸 값을 축적시켜서 분석한다.
	 */
	@Override
	public void setAnalyzePattern(HandType userHand, HandType computerHand) {
		
		if (computerHand == HandType.valueOf(0)) { // 컴퓨터가 주먹(0)을 냈을 경우
			
			if (userHand == HandType.valueOf(0)) { System.out.println("사용자는 주먹, 컴퓨터는 주먹"); analyzeCounting[0][0]++; }
			else if (userHand == HandType.valueOf(1)) { System.out.println("사용자는 가위, 컴퓨터는 주먹"); analyzeCounting[0][1]++; }
			else { System.out.println("사용자는 보, 컴퓨터는 주먹"); analyzeCounting[0][2]++; }
			
		} else if (computerHand == HandType.valueOf(1)) { // 컴퓨터가 가위(1)를 냈을 경우
			
			if (userHand == HandType.valueOf(0)) { System.out.println("사용자는 주먹, 컴퓨터는 가위"); analyzeCounting[1][0]++; }
			else if (userHand == HandType.valueOf(1)) { System.out.println("사용자는 가위, 컴퓨터는 가위"); analyzeCounting[1][1]++; }
			else { System.out.println("사용자는 보, 컴퓨터는 가위"); analyzeCounting[1][2]++; }
			
		} else { // 컴퓨터가 보(2)를 냈을 경우
			
			if (userHand == HandType.valueOf(0)) { System.out.println("사용자는 주먹, 컴퓨터는 보"); analyzeCounting[2][0]++; }
			else if (userHand == HandType.valueOf(1)) { System.out.println("사용자는 가위, 컴퓨터는 보"); analyzeCounting[2][1]++; }
			else { System.out.println("사용자는 보, 컴퓨터는 보"); analyzeCounting[2][2]++; }
			
		}
		
	}
	
	/*
	 *  축적된 결과 값에 따라 가장 많은 수를 가진 결과를 바탕으로 이길 수 있는 경우의 수로 변환해준다.
	 */
	
	public void performAnalyzePattern() {
		
		System.out.println("분석 시작~~~~~~~~~~~~!");
		
		int maxCount;
		int resultHandType = 0;
		
		if (analyzeNextHand == HandType.valueOf(0)) { // 이전에 냈던 컴퓨터 손이 주먹인 경우
			
			maxCount = analyzeCounting[0][0];
			
			for (int i = 0; i < 3; i++) {
				
				if (analyzeCounting[0][i] > maxCount) {

					maxCount = analyzeCounting[0][i];
					resultHandType = i;
					
				} else if (analyzeCounting[0][i] == maxCount) { // 만약 숫자가 같다면 둘 중 랜덤으로 하나를 선택한다
					
					int[] selectRandom = new int[] { resultHandType, i };
					resultHandType = selectRandom[rand.nextInt(1)];
					
				}
				
			}
			
			resultHandType = (resultHandType + 2) % 3;
		
		} else if (analyzeNextHand == HandType.valueOf(1)) { // 이전에 냈던 컴퓨터 손이 가위인 경우
		
			maxCount = analyzeCounting[1][0];
			
			for (int i = 0; i < 3; i++) {
				
				if (analyzeCounting[1][i] > maxCount) {

					maxCount = analyzeCounting[1][i];
					resultHandType = i;
					
				} else if (analyzeCounting[1][i] == maxCount) {
					
					int[] selectRandom = new int[] { resultHandType, i };
					resultHandType = selectRandom[rand.nextInt(1)];
					
				}
				
			}
			
			resultHandType = (resultHandType + 2) % 3;
		
		} else { // 이전에 냈던 컴퓨터 손이 보인 경우
			
			maxCount = analyzeCounting[2][0];
			
			for (int i = 0; i < 3; i++) {
				
				if (analyzeCounting[2][i] > maxCount) {

					maxCount = analyzeCounting[2][i];
					resultHandType = i;
					
				} else if (analyzeCounting[2][i] == maxCount) {
					
					int[] selectRandom = new int[] { resultHandType, i };
					resultHandType = selectRandom[rand.nextInt(1)];
					
				}
				
			}
			
			resultHandType = (resultHandType + 2) % 3;
		
		}

		analyzeNextHand = HandType.valueOf(resultHandType);
		
	}
	
}
