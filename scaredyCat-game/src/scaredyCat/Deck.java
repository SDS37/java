package scaredyCat;

class Deck {
	
	private KindOfCard kind; 
	
	// An enumerated type (enum) is a data type consisting of a set of named values 
	// elements, members or enumerators of the type. 
	
	public enum KindOfCard { 
		BirdCard, 
		ScarecrowCard, 
		CatCard
	}
	
	public Deck(KindOfCard kind) {
		this.kind = kind;
	}
	
	public KindOfCard getKind() {
		return this.kind;
	}

}
