package modules.ml.core;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstanceTest {
	
	
	@Test
	public void constuctorTest(){
		Instance inst = new Instance();
		assertEquals(0, inst.getRecords().size());
		
		inst = new Instance("AA, BB, CC", ",");
		String[] arr = new String[]{"AA", "BB", "CC"}; 
		assertArrayEquals(arr, inst.getRecords().toArray());
		
		inst = new Instance("AAA\tBBB\tCCC", "\t");
		arr = new String[]{"AAA", "BBB", "CCC"}; 
		assertArrayEquals(arr, inst.getRecords().toArray());
		
		inst = new Instance("AAA,BBB,CCC", "\t");
		arr = new String[]{"AAA,BBB,CCC"}; 
		assertArrayEquals(arr, inst.getRecords().toArray());
		
		inst = new Instance("AAA,BBB,CCC,,", ",");
		assertEquals(3, inst.getRecords().size());
		
		inst = new Instance("AAA,BBB,CCC,,DD", ",");
		assertEquals(5, inst.getRecords().size());
		
	}
	
	
	@Test
	public void removeColumnTest(){
		Instance inst = new Instance("AA, BB, CC, DD, EE", ",");
		inst.removeColumn(0);
		assertEquals("BB", inst.get(0));
		
		inst.removeColumn(1);
		assertEquals("DD", inst.get(1));
	}

	@Test
	public void removeColumnsTest(){
		Instance inst = new Instance("AA, BB, CC, DD, EE", ",");
		int[] indices = new int[]{1,3,2};
		inst.removeColumns(indices);
		assertEquals("AA", inst.get(0));
		assertEquals("EE", inst.get(1));
		assertEquals(2, inst.getRecords().size());
		
	}
	
	
	@Test
	public void addTest(){
		
		Instance inst = new Instance("AA, BB, CC, DD, EE", ",");
		inst.add("FF");
		assertEquals("FF", inst.get(5));
		inst.add("GG");
		assertEquals("GG", inst.get(6));
		
	}
	
	@Test
	public void sizeTest(){
		Instance inst = new Instance("AA, BB, CC, DD, EE", ",");
		assertEquals(5, inst.size());
		inst.add("FF");
		assertEquals(6, inst.size());
	}
	
}
