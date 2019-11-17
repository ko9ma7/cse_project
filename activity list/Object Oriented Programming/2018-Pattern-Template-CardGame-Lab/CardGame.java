import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 탬플릿 메소드 패턴
 * CardGame.java
 * 모양과 숫자
 * @author 김상진 
 */
public abstract class CardGame { // 알고리즘 틀
	public static final int TOTALNUMBEROFCARDS = 52;
	private ArrayList<Card> originalDeck 
		= new ArrayList<>(TOTALNUMBEROFCARDS);
	protected Queue<Card> remainingDeck = new ArrayDeque<>(); // 사용자에게 주고 남은 카드
	protected ArrayList<ArrayList<Card>> userCards = new ArrayList<>();
	protected int numberOfPlayers;
	// 탬플릿 메소드
	public final void init(int numberOfPlayers){
		CardFace[] cardFaces = CardFace.values();
		for(int i=0; i<52; i++){
			originalDeck.add(new Card( i % 13 + 1, cardFaces[i/13]));
		}
		shuffle();
		remainingDeck.addAll(originalDeck);
		setNumberOfPlayers(numberOfPlayers);
		dealCards();
	}
	// hook: 원하면 섞는 알고리즘을 변경할 수 있음
	// remainingDeck에 집어 넣는 것
	protected void shuffle(){
		Collections.shuffle(originalDeck);
	}
	// Concrete Method
	private void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}
	// Abstract Method
	// 카드를 각 플레이어에게 나누어주기
	protected abstract void dealCards(); // 추상 메소드로 각 하위 클래스에서 반드시 재정의해야 하는 메소드
	public ArrayList<ArrayList<Card>> getUserCards(){
		return userCards;
	}
	public void display() {
		for(var cards: userCards) {
			for(var card: cards) {
				System.out.print(card+", ");
			}
			System.out.println();
		}
	}
}
