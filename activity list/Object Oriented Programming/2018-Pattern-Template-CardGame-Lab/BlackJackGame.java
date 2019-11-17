import java.util.ArrayList;

public class BlackJackGame extends CardGame {

	@Override
	protected void dealCards() {
		
		for (int i = 0; i < numberOfPlayers; i++) {
			ArrayList<Card> userCard =new ArrayList<>();
			for (int j = 0; j < numberOfPlayers; j++) 
				userCard.add(remainingDeck.poll());
				userCards.add(userCard);
		}
		
	}

}