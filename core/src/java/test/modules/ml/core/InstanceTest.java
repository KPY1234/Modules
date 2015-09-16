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
		assertEquals(5, inst.getRecords().size());
		
	}
	
	
	

}
