package comp125;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardCollectionSizeFourTest {

	@Test
	public void testCardCollectionCardArray() {
		for(int i = 0;i<10;i++){ //Runs test 10 times with different (probably) values
			Card[] cards = new Card[4];
			for(int j=0; j<cards.length; j++)
				cards[j] = new Card(((int)(1+Math.random()*4)), ((int)(1+Math.random()*13))); //Creates a Card array full of random cards
			CardCollection collection = new CardCollection(cards);
			for(int k = 0; k<collection.getSize();k++){
				assertEquals(cards[k].getSuit(), collection.getCard(k).getSuit());
				assertEquals(cards[k].getRank(), collection.getCard(k).getRank());
				// Checks the cards in Card array 'card' are equal to those in CardCollection 'collection'
			}
		}
	}

	@Test
	public void testSort() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(4-i, 4-i); //descending order of ranks 5 4 3 2 1
		CardCollection collection = new CardCollection(cards);
		collection.sort();
		for(int i=0; i<cards.length; i++) {
			assertEquals(Math.min(4, i+1), collection.getCard(i).getSuit());
			assertEquals(Math.min(13, i+1), collection.getCard(i).getRank());
		}

		for(int i=0; i<cards.length/2; i++)
			cards[i].setRank(6);

		for(int i=cards.length/2; i<cards.length; i++) 
			cards[i].setRank(2);

		//6 6 2 2
		collection = new CardCollection(cards);
		collection.sort();
		//should be 2 2 6 6
		for(int i=0; i<cards.length/2; i++) {
			assertEquals(2, collection.getCard(i).getRank());
		}
		for(int i=cards.length/2; i<cards.length; i++) {
			assertEquals(6, collection.getCard(i).getRank());
		}
	}

	@Test
	public void testPair() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, i+1);
		CardCollection collection = new CardCollection(cards);
		assertEquals(0, collection.hasPair());
		cards[0] = new Card(1, 4);
		collection = new CardCollection(cards);
		assertEquals(4, collection.hasPair());
	}

	@Test
	public void testIsFlush() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(2, i+1);
		CardCollection collection = new CardCollection(cards);
		assertEquals(2, collection.isFlush());
		cards[2] = new Card(3, 4);
		collection = new CardCollection(cards);
		assertEquals(0, collection.isFlush());	
	}

	@Test
	public void testIsStraight() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(4-i, i+5);
		cards[0] = new Card(4, 8);
		cards[1] = new Card(1, 5);
		cards[2] = new Card(2, 6);
		cards[3] = new Card(3, 7);
		/*
		 * rank of cards (in order)
		 * {8, 5, 6, 7}
		 */
		CardCollection collection = new CardCollection(cards);
		assertEquals(5, collection.isStraight());
		cards[0] = new Card(1, 4);
		collection = new CardCollection(cards);
		/*
		 * rank of cards (in order)
		 * {4, 5, 6, 7}
		 * 
		 */
		assertEquals(4, collection.isStraight());	
		cards[3] = new Card(1, 8);
		collection = new CardCollection(cards);
		/*
		 * rank of cards (in order)
		 * {4, 5, 6, 8}
		 * 
		 */
		assertEquals(0, collection.isStraight());	
	}

	@Test
	public void testHasFourOfAKind() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, i+1);
		cards[0] = new Card(1, 4);
		cards[1] = new Card(2, 4);
		CardCollection collection = new CardCollection(cards);
		assertEquals(0, collection.hasFourOfAKind());
		cards[2] = new Card(3, 4);
		collection = new CardCollection(cards);
		assertEquals(4, collection.hasFourOfAKind());
	}

	@Test
	public void testSearch() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, i+1);
		CardCollection collection = new CardCollection(cards);
		assertTrue(collection.binarySearch(new Card(1, 1)) >= 0);
		cards[0] = new Card(1, 4);
		collection = new CardCollection(cards);
		assertEquals(-1, collection.binarySearch(new Card(1, 1)));
		assertTrue(collection.binarySearch(new Card(2, 2)) >= 0);
		cards[1] = new Card(2, 4);
		collection = new CardCollection(cards);
		assertEquals(-1, collection.binarySearch(new Card(2, 2)));
		assertEquals(-1, collection.binarySearch(new Card(2, 6)));
		assertTrue(collection.binarySearch(new Card(4, 4)) >= 0);
		cards[3] = new Card(3, 3);
		collection = new CardCollection(cards);
		assertEquals(-1, collection.binarySearch(new Card(4, 4)));
	}

	@Test
	public void testMerge() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, 4);

		CardCollection collection1 = new CardCollection(cards);

		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, 8);

		CardCollection collection2 = new CardCollection(cards);

		CardCollection collection = collection1.merge(collection2);		

		assertEquals(8, collection.getSize());
		for(int i=0; i<collection.getSize(); i++) {
			assertEquals(Math.min(4, i%4 + 1), collection.getCard(i).getSuit());
			assertEquals(Math.min(13, (i/4 + 1)*4), collection.getCard(i).getRank());
		}
	}


	@Test
	public void testGetCardCollectionInRange() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(1, i+1);

		CardCollection collection = new CardCollection(cards);

		CardCollection subset = collection.getCardCollectionInRange(1, 5);
		assertEquals(4, subset.getSize());

		subset = collection.getCardCollectionInRange(3, 4);
		assertEquals(2, subset.getSize());

		subset = collection.getCardCollectionInRange(4, 4);
		assertEquals(1, subset.getSize());

		subset = collection.getCardCollectionInRange(5, 5);
		assertEquals(0, subset.getSize());

		subset = collection.getCardCollectionInRange(4, 5);
		assertEquals(1, subset.getSize());

		subset = collection.getCardCollectionInRange(4, 3);
		assertNull(subset);
	}

	@Test
	public void testEquals() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(1, i+1);

		CardCollection collection1 = new CardCollection(cards);

		Card[] cards2 = new Card[4];
		for(int i=0; i<cards2.length; i++)
			cards2[i] = new Card(1, 4-i);

		CardCollection collection2 = new CardCollection(cards2);

		assertTrue(collection1.equals(collection2));

		cards[2] = new Card(4, 1);
		collection2 = new CardCollection(cards);
		assertFalse(collection1.equals(collection2));

		cards2 = new Card[3];
		for(int i=0; i<cards2.length; i++)
			cards2[i] = new Card(1, i+1);

		collection2 = new CardCollection(cards2);
		assertFalse(collection1.equals(collection2));
	}
}
