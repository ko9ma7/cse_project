import java.util.ArrayList;

public class PokerGame extends CardGame {

	private static int NUMBEROFCARDSPERPLAYER = 5;
	
	@Override
	protected void dealCards() {
		for (int i = 0; i < NUMBEROFCARDSPERPLAYER; i++) {
			ArrayList<Card> userCard = new ArrayList<>();
			userCards.add(userCard);
		}
		
		for (int i = 0; i < NUMBEROFCARDSPERPLAYER; i++) {
			for (var userCard : userCards) 
				userCard.add(remainingDeck.poll());
		}
	}
	
}
