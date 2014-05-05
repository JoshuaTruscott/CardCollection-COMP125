package comp125;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardCollectionAnySizeTest {

	@Test
	public void testCardCollectionCardArray() {
		for(int i = 0; i<10;i++){ //10 repetitions
			Card[] cards = new Card[(int)Math.random()*1000]; //Creates an array from size 0-1000
			for(int j=0;j<cards.length; j++)
				cards[j] = new Card(((int)(1+Math.random()*4)), ((int)(1+Math.random()*13)));
			CardCollection collection = new CardCollection(cards); //Creates a collection from created card array
			for(int k = 0; k<collection.getSize();k++){ //For every card in collection
				assertEquals(cards[k].getSuit(), collection.getCard(k).getSuit()); //Check suit is initialized correctly
				assertEquals(cards[k].getRank(), collection.getCard(k).getRank()); //Check rank is initialized correctly
			}
		}
	}
	@Test
	public void testSort() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(4-i, 4-i); //descending order of ranks 4 3 2 1
		CardCollection collection = new CardCollection(cards);
		collection.sort();
		for(int i=0; i<cards.length; i++) {
			assertEquals(i+1, collection.getCard(i).getSuit());
			assertEquals(i+1, collection.getCard(i).getRank());
		}

		for(int i=0; i<cards.length/2; i++)
			cards[i].setRank(6);

		for(int i=cards.length/2; i<cards.length; i++) 
			cards[i].setRank(2);

		//6 6 2 2 
		collection = new CardCollection(cards);
		collection.sort();

		for(int i=0; i<cards.length/2; i++) {
			assertEquals(2, collection.getCard(i).getRank());
		}
		for(int i=cards.length/2; i<cards.length; i++) {
			assertEquals(6, collection.getCard(i).getRank());
		}
	}

	@Test
	public void testPair() {
		Card[] cards = new Card[12];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, i+1);
		CardCollection collection = new CardCollection(cards);
		assertEquals(0, collection.hasPair());
		cards[0] = new Card(1, 5);
		collection = new CardCollection(cards);
		assertEquals(5, collection.hasPair());
	}

	@Test
	public void testIsFlush() {
		Card[] cards = new Card[8];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(2, i+1);
		CardCollection collection = new CardCollection(cards);
		assertEquals(2, collection.isFlush());
		cards[2] = new Card(3, 5);
		collection = new CardCollection(cards);
		assertEquals(0, collection.isFlush());	
	}

	@Test
	public void testIsStraight() {
		Card[] cards = new Card[8];
		cards[0] = new Card(4, 8);
		cards[1] = new Card(1, 5);
		cards[2] = new Card(2, 6);
		cards[3] = new Card(3, 9);
		cards[4] = new Card(1, 11);
		cards[5] = new Card(2, 12);
		cards[6] = new Card(3, 10);
		cards[7] = new Card(3, 7);
		/*
		 * rank of cards (in order)
		 * {8, 5, 6, 9, 11, 12, 10, 7}
		 * 
		 * even if you sort them,
		 * {5, 11, 6, 12, 7, 9, 10, 8}
		 */
		CardCollection collection = new CardCollection(cards);
		assertEquals(5, collection.isStraight());
		cards[1] = new Card(1, 13);
		collection = new CardCollection(cards);
		/*
		 * rank of cards (in order)
		 * {8, 13, 6, 9, 11, 12, 10, 7}
		 */
		assertEquals(6, collection.isStraight());	
		cards[1] = new Card(1, 4);
		/*
		 * rank of cards (in order)
		 * {8, 4, 6, 9, 11, 12, 10, 7}
		 */
		collection = new CardCollection(cards);
		assertEquals(0, collection.isStraight());	
	}

	@Test
	public void testHasFourOfAKind() {
		Card[] cards = new Card[10];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, i+1);
		cards[0] = new Card(1, 4);
		cards[1] = new Card(2, 4);
		CardCollection collection = new CardCollection(cards);
		assertEquals(0, collection.hasFourOfAKind());
		cards[2] = new Card(3, 3);
		cards[4] = new Card(3, 4);
		collection = new CardCollection(cards);
		assertEquals(4, collection.hasFourOfAKind());
	}

	@Test
	public void testSearch() {
		Card[] cards = new Card[5];
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
		cards[4] = new Card(4, 3);
		collection = new CardCollection(cards);
		assertEquals(-1, collection.binarySearch(new Card(5, 5)));
	}

	@Test
	public void testMerge() {
		Card[] cards = new Card[4];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, 5);

		CardCollection collection1 = new CardCollection(cards);

		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(i+1, 10);

		CardCollection collection2 = new CardCollection(cards);

		CardCollection collection = collection1.merge(collection2);		

		assertEquals(8, collection.getSize());
		for(int i=0; i<collection.getSize(); i++) {
			assertEquals(i%4 + 1, collection.getCard(i).getSuit());
			assertEquals((i/4 + 1)*5, collection.getCard(i).getRank());
		}
	}


	@Test
	public void testGetCardCollectionInRange() {
		Card[] cards = new Card[10];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(1, i+1);

		CardCollection collection = new CardCollection(cards);

		CardCollection subset = collection.getCardCollectionInRange(1, 10);
		assertEquals(10, subset.getSize());

		subset = collection.getCardCollectionInRange(4, 10);
		assertEquals(7, subset.getSize());

		subset = collection.getCardCollectionInRange(10, 10);
		assertEquals(1, subset.getSize());

		subset = collection.getCardCollectionInRange(11, 13);
		assertEquals(0, subset.getSize());

		subset = collection.getCardCollectionInRange(11, 10);
		assertNull(subset);
	}

	@Test
	public void testEquals() {
		Card[] cards = new Card[10];
		for(int i=0; i<cards.length; i++)
			cards[i] = new Card(1, i+1);

		CardCollection collection1 = new CardCollection(cards);

		Card[] cards2 = new Card[10];
		for(int i=0; i<cards2.length; i++)
			cards2[i] = new Card(1, 10-i);

		CardCollection collection2 = new CardCollection(cards2);

		assertTrue(collection1.equals(collection2));

		cards[5] = new Card(4, 1);
		collection2 = new CardCollection(cards);
		assertFalse(collection1.equals(collection2));

		cards2 = new Card[9];
		for(int i=0; i<cards2.length; i++)
			cards2[i] = new Card(1, i+1);

		collection2 = new CardCollection(cards2);
		assertFalse(collection1.equals(collection2));
	}
}
