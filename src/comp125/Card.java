package comp125;


import java.util.Random;

/**
 * 
 * Card encodes a playing card that contains a suit and rank.
 * suit can be an integer from 1 to 4
 * 
 * Suits - 
 * 1 representing clubs, 
 * 2 representing diamonds,
 * 3 representing hearts, 
 * 4 representing spades
 * 
 * Rank - 
 * 1 representing Ace
 * 2 to 10 representing their numerical valued card
 * 11 representing jack
 * 12 representing queen
 * 13 representing king
 * 
 * @author gauravgupta
 *
 */
public class Card {
	private int suit;
	private int rank;

	/**
	 * default constructor, should invoke setters for assigning values to data members
	 * by passing a value between 1 and 13 (inclusive) for rank, and a value between 1 and 4 (inclusive) for suit
	 */
	public Card() {
		Random r = new Random();
		setSuit(1 + r.nextInt(13));
		setRank(1 + r.nextInt(4));
	}
	
	/**
	 * parameterized constructor, should invoke setters for assigning values to data members
	 * @param mySuit
	 * @param myRank
	 */
	public Card(int mySuit, int myRank) {
		setSuit(mySuit);
		setRank(myRank);
	}
	
	/**
	 * set the rank of the card, constrained between 1 and 13 (inclusive)
	 * if input is less than 1, data member rank should become 1
	 * else if input is more than 13, data member rank should become 13
	 * else data member rank should become input value
	 * @param myRank
	 */
	public void setRank(int myRank) {
		if(myRank < 1)
			myRank = 1;
		if(myRank > 13)
			myRank = 13;
		rank = myRank;
	}
	
	/**
	 * set the suit of the card, constrained between 1 and 4 (inclusive)
	 * if input is less than 1, data member suit should become 1
	 * else if input is more than 4, data member suit should become 4
	 * else data member suit should become input value
	 * @param mySuit
	 */
	public void setSuit(int mySuit) {
		if(mySuit < 1)
			mySuit = 1;
		if(mySuit > 4)
			mySuit = 4;
		suit = mySuit;
	}

	//getters
	
	public int getRank() {
		return rank;
	}

	public int getSuit() {
		return suit;
	}

	/**
	 * get the String value of the rank. for ranks between 1 and 10 (inclusive), return 
	 * the numerical value itself (for example, if rank is 4, return "4"). 
	 * for rank 1, return "Ace"
	 * for rank 11, return "Jack"
	 * for rank 12, return "Queen"
	 * for rank 13, return "King"
	 * @return
	 */
	public String rankString() {
		if(rank > 1 && rank < 11)
			return rank+"";
		if(rank == 1)
			return "Ace";
		if(rank == 11)
			return "Jack";
		if(rank == 12)
			return "Queen";
		return "King";
		
		/*
		 * another possible solution
		 * 
		 * String[] rankNames = {"Ace", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
		 * return rankNames[rank-1];
		 */
	}
	
	/**
	 * get the String value of the suit. 
	 * for suit 1, return "Clubs"
	 * for suit 2, return "Diamonds"
	 * for suit 3, return "Hearts"
	 * for suit 4, return "Spades"
	 * @return
	 */
	public String suitString() {
		if(suit == 1)
			return "Clubs";
		if(suit == 2)
			return "Diamonds";
		if(suit == 3)
			return "Hearts";
		return "Spades";
		
		/*
		 * another possible solution -
		 *
		 * String[] suitNames = {"Clubs", "Diamonds", "Hearts", "Spades"};
		 * return suitNames[suit-1];
		 */
	}
	
	/**
	 * @return text representation of the object. For example if rank is 3 and suit is 4,
	 * it should return "3 of Spades", and if rank is 11 and suit is 2, it should return "Jack of Diamonds"
	 * HINT: use {@link #suitString()} and {@link #rankString()} methods
	 */
	public String toString() {
		return  rankString() +" of "+suitString();
	}
	
	/**
	 * 
	 * @param other
	 * @return 
	 * 
	 * positive value, if card object on which method is called is "more than" card object passed as parameter.
	 * negative value, if card object on which method is called is "less than" card object passed as parameter.
	 * zero, if card object on which method is called is "equal to" card object passed as parameter.
	 * 
	 * card1 is "more than" card2 if:
	 * 		a. card1.suit > card2.suit, or,
	 * 		b. card1.suit == card2.suit && card1.rank > card1.rank
	 * 
	 * card1 is "less than" card2 if:
	 * 		a. card1.suit < card2.suit, or,
	 * 		b. card1.suit == card2.suit && card1.rank < card1.rank
	 * 
	 * card1 is "equal to" card2 if:
	 * 		card1.suit == card2.suit && card1.rank == card1.rank
	 * 
	 */
	public int compareTo(Card other) {
		if(suit != other.suit)
			return suit - other.suit;
		return rank - other.rank;
	}
	
	/**
	 * 
	 * TO BE COMPLETED
	 * 
	 * @param other
	 * @return true if the card object on which the method is called has the same suit and rank
	 * as the card object passed as the parameter.  
	 */
	public boolean equals(Card other) {
		if(suit == other.suit && rank == other.rank) //Compares suits and rankks
			return true; //True if match
		else
			return false; //False otherwise
	}
}
