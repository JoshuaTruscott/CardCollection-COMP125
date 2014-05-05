package comp125;

import java.util.Arrays;

/**
 * A CardCollection is a set of cards. It is possible to have multiple instances of the same kind of card
 * in the set. For example, a set may have two Ace of Clubs.
 * @author gauravgupta
 *
 */
public class CardCollection {
	private Card[] cards;
	private boolean isSorted;

	/**
	 * DO NOT MODIFY
	 * Create a deep copy of the parameter array c into data member array cards
	 * 
	 * example of shallow copy (b into a)
	 * Rectangle[] a = b; //where b is some Rectangle[]
	 * 
	 * deep copy (b into a)
	 * Rectangle[] a = new Rectangle[b.length];
	 * for(int i=0; i<a.length; i++)
	 * 		a = new Rectangle(b.getLength(), b.getBreadth());
	 * 
	 * 
	 * @param c
	 */
	public CardCollection(Card[] c) {
		if(c != null) {
			cards = new Card[c.length];
			for(int i=0; i<cards.length; i++)
				cards[i] = new Card(c[i].getSuit(), c[i].getRank());
		}
		isSorted = false;
	}

	/**
	 * DO NOT MODIFY
	 * @return the length of cards array
	 */
	public int getSize() {
		return cards.length;
	}

	/**
	 * DO NOT MODIFY
	 * get card object from cards array at index i, provided i is a valid index
	 * 
	 * @param i
	 * @return card object at index i if i is valid index, null otherwise
	 */
	public Card getCard(int i) {
		if(i >= 0 && i < cards.length)
			return cards[i];
		return null;
	}

	/**
	 * DO NOT MODIFY
	 * return canonical representation of the objects
	 */
	public String toString() {
		String result = "";
		for(int i=0; i<cards.length; i++) {
			result = result + cards[i].toString() + ", ";
		}
		return result.substring(0, result.length()-2);
	}
	
	/**
	 * TO BE COMPLETED
	 * a pair, is technically a poker term, but can be generalized to any
	 * card collection. it implies that the collection has (at least) two cards
	 * of the same rank 
	 * @return the rank of which there are (at least) two cards, and 0 otherwise.
	 */	
	public int hasPair() {
		 for(int i = 0; i<cards.length-1; i++){
			for(int k = i+1;k<cards.length;k++)
			if(cards[i].getRank() == cards[k].getRank())
				return cards[i].getRank();
		}
		 return 0; //Returns pair if found, otherwise returns 0 
	 }

	 /**
	 * TO BE COMPLETED
	  * a flush is when all cards of a collection have the same suit.
	  * @return the suit to which all cards belong if there's a flush, and 0 otherwise
	  */
	 public int isFlush() {
		 for(int i = 1; i<cards.length;i++)
			 if(cards[i].getSuit() != cards[i-1].getSuit())
				 return 0; //If any card's suit isn't the same as the previous card's suit, return 0
		 return cards[0].getSuit();
	 }

	 /**
	 * TO BE COMPLETED
	  * a straight is when the ranks of all cards of a collection are in sequence.
	  * for example, if the ranks of the cards are {1, 2, 3, 4} (in sequence with least rank 1)
	  * or {2, 3, 4, 5, 6, 7}, (in sequence with least rank 2)
	  * or {8, 9, 10}, (in sequence with least rank 8)
	  * or {9, 10, 11, 12, 13} (in sequence with least rank 9)
	  * of {8, 9, 10, 12} (NOT IN SEQUENCE)
	  * @return the least rank if all ranks (of a sorted collection) in sequence, and 0 otherwise
	  */
	 public int isStraight() {
		 int[] tempArray = new int[cards.length];
		 for(int i = 0; i<cards.length;i++)
			 tempArray[i] = cards[i].getRank();
		 Arrays.sort(tempArray);
		 for(int i = 0; i<cards.length-1;i++)
			 if(tempArray[i] != tempArray[i+1]-1)
				 return 0;
			 
		 return tempArray[0];
	 }

	 /**
	 * TO BE COMPLETED
	  * four of a kind, is technically a poker term, but can be generalized to any
	  * card collection. it implies that the collection has (at least) four cards
	  * of the same rank 
	  * @return the rank of which there are (at least) four cards, and 0 otherwise.
	  */
	 public int hasFourOfAKind() {
		 int[] tempArray = new int[13];
		 tempArray[cards[0].getRank()-1] += 1; //Creates array to count each ranks frequency
		 for(int i = 1; i<cards.length;i++){
				 tempArray[cards[i].getRank()-1] += 1;
		 	if(tempArray[cards[i].getRank()-1] == 4) //If frequency reaches 4 returns rank
		 		return cards[i].getRank();
		 }
		 return 0;
	 }
	 
	 /**
	  * sorts the cards of the card collection in ascending order based on Card.compareTo(Card)
	  * 
	  * card1 > card2 if card1.compareTo(card2) > 0
	  * card1 == card2 if card1.compareTo(card2) == 0
	  * card1 < card2 if card1.compareTo(card2) < 0
	  * 
	  * provide the best case, average case, and worst case complexity of your 
	  * algorithm below 
	  * BEST CASE = O(1)
	  * AVERAGE CASE = O((N^2)/2)
	  * WORST CASE = O(N^2)
	  */
	public void sort() { //Uses selection sort to sort the cards array
		if(cards == null)
			return;
		
		for(int i=0; i<cards.length-1; i++) {
			int minIndex = i; //assume first item in the leftover array is the smallest
			for(int k=i+1; k<cards.length; k++) {
				if(cards[k].compareTo(cards[minIndex])<0) { //if an item smaller than the current smallest found
					minIndex = k; //now the smallest item is at index k
				}
			}
			
			Card temp = cards[i];
			cards[i] = cards[minIndex]; 
			cards[minIndex] = temp;
		}
		isSorted = true;
	}


	 /**
  	  * TO BE COMPLETED
	  * CHECK IF SORTED FIRST
	  * implement binary search based on Card.compareTo(Card) to look for a target card.
	  * if target.compareto(cards[mid]) == 0, return mid
	  * if target.compareto(cards[mid]) < 0, look in left half
	  * if target.compareto(cards[mid]) > 0, look in right half
	  * 
	  * @param target
	  * @return the index in the sorted array where target is found (not necessarily the index
	  * of the first occurrence, given we are using binary search algorithm), and return -1 if not found
	  */
	 public int binarySearch(Card target) {
		 for(int i=1;i<cards.length;i++){
			 if(cards[i].compareTo(cards[i-1]) < 0)
				 sort();
		 }
		 int lowest = 0;
	        int highest = cards.length - 1;
	        while (lowest <= highest) {
	            int mid = lowest + (highest - lowest) / 2; //find the median location
	            if      (target.compareTo(cards[mid]) < 0) highest = mid - 1;
	            else if (target.compareTo(cards[mid]) > 0) lowest = mid + 1;
	            else return mid;
	        }
	        return -1;
	 }

	 /**
 	  * TO BE COMPLETED
	  * compares two card collections for equality, not considering the order of the cards.
	  * for example - 
	  * collection 1 = {4 of hearts, jack of clubs, 9 of spades}, and 
	  * collection 2 = {jack of clubs, 9 of spades, 4 of hearts} are equal
	  * 
	  * @param other
	  * @return true if the object on which the method is called is equal to the object passed
	  */
	 public boolean equals(CardCollection other) {
		 sort();
		 other.sort();
		 if(cards.length != other.cards.length)
			 return false;
		for(int i = 0; i<cards.length;i++)
				 if(cards[i].getRank() != other.cards[i].getRank() || cards[i].getSuit() != other.cards[i].getSuit())
					 return false;
		 return true;
	 }

	 /**
 	  * TO BE COMPLETED
	  * 
	  * @param other
	  * @return the card collection resulting from merging two card collections, 
	  * in the order of the card collection on which the method is called followed 
	  * by the card collection passed as the parameter
	  */
	 public CardCollection merge(CardCollection other) {
		 /**
		  * Creates an array of the size (cards.length + other.cards.length), then starts a loop
		  * that populates the new array with the items from the local array, then secondly
		  * populates it with the items from the parameterized array 
		  */
		 Card[] c = new Card[cards.length + other.cards.length];
		 for(int i = 0;i<cards.length;i++)
			 c[i] = cards[i];
		 for(int i = cards.length;i<(cards.length+other.cards.length);i++)
			 c[i] = other.cards[i-cards.length];
		 CardCollection merger = new CardCollection(c);
		 return merger;
	 }

	 /**
 	  * TO BE COMPLETED
	  * 
	  * @param lowRank
	  * @param highRank
	  * @return a CardCollection consisting of cards from the collection on which the method is called,
	  * that have a lowRank <= rank <= highRank
	  * return null if lowRank > highRank
	  * return CardCollection consisting of an array of Cards of size 0 if there are no cards in this collection that 
	  * satisfy the criteria. 
	  */
	 public CardCollection getCardCollectionInRange(int lowRank, int highRank) {
		 /**
		  * Finds the amount of cards that match the criteria, then creates an Card array of that size and
		  * populates it with those cards
		  */
		if(lowRank > highRank)
			return null;
		 int total = 0;
		 int index = 0;
		 for(int i=0;i<cards.length;i++)
			if(cards[i].getRank() >= lowRank && cards[i].getRank() <= highRank)
				total += 1;
		 Card[] c = new Card[total];
		 for(int i=0;i<cards.length;i++)
			if(cards[i].getRank() >= lowRank && cards[i].getRank() <= highRank){
				c[index] = cards[i];
				index += 1;
			}
		 CardCollection cc = new CardCollection(c);
		 return cc;
	 }
}
