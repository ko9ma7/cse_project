/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * PlayingStrategy
 * 컴퓨터의 묵찌바를 결정할 떄 사용할 수 있는 전략의 추상 타입
 * @author 김상진
 *
 */
public interface PlayingStrategy {

	HandType nextHand();
	void setLastUserHand(HandType hand); // LastHandBasedStrategy를 사용하기 위해선 메소드가 하나 더 필요함 -> @Override가 필요함
//  defualt void setLastUserHand(HandType hand) {}; Java 8부터는 interface에 디폴트 메소드를 넣을 수 있게 해주어서 따로 다른 전략패턴을 추가할 때마다 수정할 필요가 없다  

	public void setLastWinComputerHand(HandType computerHand);
	public void setLastLoseComputerHand(HandType computerHand);
	public void setAnalyzePattern(HandType userHand, HandType computerHand);
	
}
