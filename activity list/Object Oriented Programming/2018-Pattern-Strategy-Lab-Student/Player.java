/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2018년도 2학기 전략패턴 실습
 * Player
 * 컴퓨터와 사용자 플레이어를 아우르는 추상타입
 * 추상 클래스가 아니라 interface로도 구현 가능
 * @author 김상진
 *
 */
public abstract class Player {
	public void setHand(HandType hand) {}
	public abstract HandType nextHand();
}
