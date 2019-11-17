/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * UserPlayer
 * 사용자의 묵찌바를 결정하는 클래스
 * @author 김상진
 *
 */
public class UserPlayer extends Player {
	private HandType hand;
	@Override
	public void setHand(HandType hand) {
		this.hand = hand;
	}
	@Override
	public HandType nextHand() {
		return hand;
	}
}
