package modules.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modules.utilities.ArrayHandler;


public class ArrayHandlerTest {
	
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
		
		outContent.reset();
		String[] strs = new String[]{"AA","BB","CC"};
		ArrayHandler.printArray(strs, ",");
		assertEquals("AA,BB,CC", outContent.toString());
		ArrayHandler.printArray(strs, "\t");
		assertEquals("AA,BB,CCAA	BB	CC", outContent.toString());
		ArrayHandler.printArray(strs, "\n");
		assertEquals("AA,BB,CCAA	BB	CCAA\nBB\nCC", outContent.toString());
		
		outContent.reset();
		int[] ints = new int[]{1,2,3,4,};
		ArrayHandler.printArray(ints, ",");
		assertEquals("1,2,3,4", outContent.toString());
		
		outContent.reset();
		double[] doubles = new double[]{1.2,3.3,6.6,4.5};
		ArrayHandler.printArray(doubles, ",");
		assertEquals("1.2,3.3,6.6,4.5", outContent.toString());
	}
	
	@Test
	public void addTest(){
		
		outContent.reset();
		String[] strs = new String[]{"AA","BB","CC"};
		strs = (String[]) ArrayHandler.add(strs, "DD");
		ArrayHandler.printArray(strs, ",");
		assertEquals("AA,BB,CC,DD", outContent.toString());
		
		outContent.reset();
		int[] ints = new int[]{1,2,3};
		ints = ArrayHandler.add(ints, 4);
		ArrayHandler.printArray(ints, ",");
		assertEquals("1,2,3,4", outContent.toString());
		
		outContent.reset();
		double[] doubles = new double[]{1.1,2.2,3.3};
		doubles = ArrayHandler.add(doubles, 4.4);
		ArrayHandler.printArray(doubles, ",");
		assertEquals("1.1,2.2,3.3,4.4", outContent.toString());
		
	}
	
	@Test
	public void removeTest(){
		
		String[] strs = new String[]{"AA", "BB", "CC"};
		String[] removedStrs = new String[]{"AA", "BB"};
		assertArrayEquals(removedStrs, ArrayHandler.remove(strs, 2));
		
		strs = new String[]{"AA", "BB", "CC"};
		removedStrs = new String[]{"BB", "CC"};
		assertArrayEquals(removedStrs, ArrayHandler.remove(strs, 0));
	}
	
	
}
