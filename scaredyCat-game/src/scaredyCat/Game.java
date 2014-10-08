package scaredyCat;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import scaredyCat.Deck.KindOfCard;

public class Game {
	
	private static ArrayList<Deck> deck;
	private static ArrayList<Player> players;
	private static boolean scarecrowsOnDeck;
	private static int scarecrowCounter;
	private static int finalNumberOfBirdCards;

	public static void main(String[] args) {
		
		creatingTheCards();
		shuffle();
		gameOn();
		
	}

	public static void creatingTheCards() { // Creates the cards
		
		deck = new ArrayList<Deck>();

		for (int i = 1; i <= 40; i++) {			
			deck.add(new Deck(KindOfCard.BirdCard));
		}

		for (int i = 1; i <= 6; i++) {
			deck.add(new Deck(KindOfCard.ScarecrowCard));
		}

		for (int i = 1; i <= 3; i++) {
			deck.add(new Deck(KindOfCard.CatCard));
		}
		
	}

	public static void createPlayers(int numOfPlayers) { // Creates the number of players on an arrayList
		
		String playerName;
		players = new ArrayList<Player>();

		for (int i = 0; i < numOfPlayers; i++) {
			playerName = JOptionPane.showInputDialog("Name of Player: " + (i + 1));
			players.add(new Player(playerName));
		}
		
	}

	public static void shuffle() {
		
		Collections.shuffle(deck); // Using java.util.Collections the cards are shuffled
		
	}

	public static void gameOn() {
		
		int numOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Number of players?")), 
		playerTurn = 0;
		
		Player currentPlayer;
		Deck selectedCard;
		ArrayList<Deck> currentPlayerCards;
		
		String resultMessage = "", catMessage = "";
		
		createPlayers(numOfPlayers);

		scarecrowsOnDeck = true;
		while (scarecrowsOnDeck) {
			
			currentPlayer = players.get(playerTurn);
			
			// Result message

			resultMessage = "There are " + deck.size() + " cards on the deck.\n\nScarecrow cards found: " + scarecrowCounter + ".\n\n";
			
			resultMessage += "Player's turn: " + currentPlayer.getName().toUpperCase() + "\n\nRESULTS TABLE:\n";
			
			for (int i = 0; i < players.size(); i++) {
				
			resultMessage += players.get(i).getName().toUpperCase() + " has: " + players.get(i).getNumberOfBirdCards() + " Bird cards.\n";
			}

			selectedCard = deck.remove(deck.size() - 1);
			
			resultMessage += "\n" + catMessage;

			JOptionPane.showMessageDialog(null, resultMessage);

			catMessage = "";

			if (selectedCard.getKind() == Deck.KindOfCard.BirdCard) {
				currentPlayer.addCard(selectedCard);
			} else if (selectedCard.getKind() == Deck.KindOfCard.ScarecrowCard) {
				scarecrowCounter++;
				if (scarecrowCounter == 6) {
					scarecrowsOnDeck = false;
				}
			} else {
				catMessage = "A cat has scared all of your birds " + currentPlayer.getName().toUpperCase() + ".";
				deck.add(selectedCard);
				currentPlayerCards = currentPlayer.getCards();
				for (Deck card : currentPlayerCards) {
					deck.add(card);
				}
				currentPlayer.clearPlayerCards();
				shuffle();
			}
			playerTurn++;
			if (playerTurn > numOfPlayers - 1) {
				playerTurn = 0;
			}
			
		} gameOver();
		
	}

	public static ArrayList<String> getWinners() { // Gets the name or the names (when there is a tie), of the winners
		
		int playerScore = 0;

		finalNumberOfBirdCards = 0;

		ArrayList<String> namesOfWinners = new ArrayList<String>();

		for (Player player : players) {
			playerScore = player.getNumberOfBirdCards();
			if (playerScore >= finalNumberOfBirdCards) {
				finalNumberOfBirdCards = player.getNumberOfBirdCards();
			}
		}

		for (Player player : players) {
			if (player.getNumberOfBirdCards() == finalNumberOfBirdCards) {
				namesOfWinners.add(player.getName());
			}
		}
		return namesOfWinners;
		
	}

	public static void gameOver() {
		
		ArrayList<String> namesOfWinners = getWinners();

		String winnersString = "";

		for (int i = 0; i < namesOfWinners.size(); i++) {
			winnersString += namesOfWinners.get(i);
			if (i < namesOfWinners.size() - 1) {
				winnersString += ", "; // List of winners
			}
		}
		
		JOptionPane.showMessageDialog(null, "The 6 scarecrows were found!\n\nYou have won: " + winnersString.toUpperCase() + " !\nYou have collected " + finalNumberOfBirdCards + " bird card(s).");
		
	}
}