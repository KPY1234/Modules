package modules.utilities.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class StandardIOTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	
	@Before
	public void setupStreams(){
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void cleanStreams(){
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void printArrayTest() {
		
		String[] strs = new String[]{"AA","BB","CC"};
		StandardIO.printArray(strs, ",");
		assertEquals("AA,BB,CC", outContent.toString());
		StandardIO.printArray(strs, "\t");
		assertEquals("AA,BB,CCAA	BB	CC", outContent.toString());
		StandardIO.printArray(strs, "\n");
		assertEquals("AA,BB,CCAA	BB	CCAA\nBB\nCC", outContent.toString());
		
		outContent.reset();
		int[] ints = new int[]{1,2,3,4,};
		StandardIO.printArray(ints, ",");
		assertEquals("1,2,3,4", outContent.toString());
		
		outContent.reset();
		double[] doubles = new double[]{1.2,3.3,6.6,4.5};
		StandardIO.printArray(doubles, ",");
		assertEquals("1.2,3.3,6.6,4.5", outContent.toString());
	}
}
