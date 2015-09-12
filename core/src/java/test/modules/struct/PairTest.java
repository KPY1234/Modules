package modules.struct;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import modules.struct.Pair;


public class PairTest {

	
	@Test
	public void getLift() {
		Pair<String, Integer> p = new Pair<String, Integer>("AA",5);
		assertEquals("AA", p.getLeft());
	}
	
	@Test
	public void getRight() {
		Pair<String, Integer> p = new Pair<String, Integer>("AA",5);
		assertEquals(5, (int)p.getRight());
	}
	
}
