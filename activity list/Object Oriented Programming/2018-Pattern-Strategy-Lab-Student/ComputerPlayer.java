/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * ComputerPlayer
 * 컴퓨터의 묵찌바를 결정하는 클래스
 * 묵찌바 전략을 strategy에 설정하고 이를 이용하여 다음에 낼 손을 결정함
 * @author 김상진
 *
 */
public class ComputerPlayer extends Player {
	private PlayingStrategy strategy;
	public ComputerPlayer() {
	}
	public void setStrategy(PlayingStrategy strategy) {
		this.strategy = strategy;
	}
	public HandType nextHand(){
		return HandType.MOOK;
	}
}
