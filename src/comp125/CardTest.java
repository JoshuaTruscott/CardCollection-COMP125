package comp125;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {
	@Test
	public void testCompareTo() {
		Card c1 = new Card(2, 2);
		Card c2 = new Card(2, 2);
		assertEquals(0,c1.compareTo(c2));
		c2 = new Card(3, 2);
		assertTrue(c1.compareTo(c2)<0);
		c1 = new Card(3, 5);
		assertTrue(c1.compareTo(c2)>0);
		c2 = new Card(3, 5);
		assertEquals(0,c1.compareTo(c2));
	}

	@Test
	public void testEquals() {
		Card c1 = new Card(1, 1);
		Card c2 = new Card(1, 1);
		assertTrue(c1.equals(c2));
		c2 = new Card(1, 2);
		assertFalse(c1.equals(c2));
		c2 = new Card(2, 1);
		assertFalse(c1.equals(c2));
		c1 = new Card(2, 1);
		assertTrue(c1.equals(c2));	
	}
}
