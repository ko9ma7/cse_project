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
}
