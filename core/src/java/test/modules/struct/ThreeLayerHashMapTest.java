package modules.struct;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import modules.struct.Pair;
import modules.struct.ThreeLayerHashMap;

public class ThreeLayerHashMapTest {
	
	@Test
	public void get() {
		ThreeLayerHashMap<Integer,Integer,String> tlhm = 
					new ThreeLayerHashMap<Integer,Integer,String>();
		
		tlhm.put(1, 1, "Test1");
		tlhm.put(1, 2, "Test2");
		tlhm.put(2, 2, "Test3");
		assertEquals("Test1", tlhm.get(1, 1));
		assertEquals("Test2", tlhm.get(1, 2));
		assertEquals("Test3", tlhm.get(2, 2));
		tlhm.put(1, 1, "Test4");
		assertEquals("Test4", tlhm.get(1, 1));
	}
	
	@Test
	public void keys() {
		ThreeLayerHashMap<Integer,Integer,String> tlhm = 
					new ThreeLayerHashMap<Integer,Integer,String>();
		
		tlhm.put(1, 1, "Test1");
		tlhm.put(1, 2, "Test2");
		tlhm.put(2, 2, "Test3");
		
		ArrayList<Pair> keys = new ArrayList<Pair>();
		keys.add(new Pair<Integer,Integer>(1,1));
		keys.add(new Pair<Integer,Integer>(1,2));
		keys.add(new Pair<Integer,Integer>(2,2));
		
		assertEquals(keys.size(), tlhm.keys().size());

		tlhm.put(1, 1, "Test4");
		assertEquals(keys.size(), tlhm.keys().size());
		
		tlhm.put(1, 5, "Test5");
		keys.add(new Pair<Integer,Integer>(1,5));
		assertEquals(keys.size(), tlhm.keys().size());
	}
	
	@Test
	public void containKey(){
		
		ThreeLayerHashMap<Integer,Integer,String> tlhm = 
				new ThreeLayerHashMap<Integer,Integer,String>();
	
		tlhm.put(1, 1, "Test1");
		tlhm.put(1, 2, "Test2");
		tlhm.put(2, 2, "Test3");
		
		assertEquals(true, tlhm.containKey(1, 1));
		assertEquals(true, tlhm.containKey(1, 2));
		assertEquals(true, tlhm.containKey(2, 2));
		assertEquals(false, tlhm.containKey(3, 2));
	} 
}
