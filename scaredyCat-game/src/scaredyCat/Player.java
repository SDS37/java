package scaredyCat;

import java.util.ArrayList;

class Player {
	
	private String name;
	private ArrayList<Deck> cards;
	private int numberOfBirdCards;
	
	public Player(String name) {
		this.name = name;
		this.cards = new ArrayList<Deck>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Deck> getCards() {
		return this.cards;
	}
	
	public int getNumberOfBirdCards() {
		return this.numberOfBirdCards;
	}
	
	public void addCard(Deck birdCard) {
		this.cards.add(birdCard);
		this.numberOfBirdCards ++;
		
	}
	
	public void clearPlayerCards() {
		this.cards.clear();
		this.numberOfBirdCards = 0;
	}
	
}