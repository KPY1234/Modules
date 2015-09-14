package modules.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import modules.reader.CSVReader;
import modules.struct.Tuple;


public class CSVReaderTest {
	
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void readTest() {
		CSVReader csvr = new CSVReader();
		try {
			csvr.read("./TestData/CSVReaderTest.csv", 5);
			assertEquals("12345", csvr.getContentTuples().get(0).getCell(0).toString());
			assertEquals("\"135,350.56\"", csvr.getContentTuples().get(2).getCell(1).toString());
			assertEquals("tt", csvr.getContentTuples().get(3).getCell(2).toString());
			
			csvr.read("./TestData/CSVReaderTest.csv", 5, 6);
			assertEquals("12345", csvr.getContentTuples().get(0).getCell(0).toString());
			assertEquals("ww", csvr.getContentTuples().get(1).getCell(1).toString());
			csvr.getContentTuples().get(2).getCell(1).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getDataFromColumnsTest(){
		
		CSVReader csvr = new CSVReader();
		try {
			csvr.read("./TestData/CSVReaderTest.csv", 1);
			ArrayList<Tuple> tuples = csvr.getDataFromColumns(1, 2, 3);
			assertEquals("bb", tuples.get(0).getCell(0).getValue());
			assertEquals("cc", tuples.get(0).getCell(1).getValue());
			assertEquals("Rock", tuples.get(3).getCell(0).getValue());
			assertEquals("33", tuples.get(4).getCell(2).getValue());
			assertEquals("tt", tuples.get(7).getCell(1).getValue());
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(10);
			list.add(7);
			tuples = csvr.getDataFromColumns(list);
			assertEquals("bb", tuples.get(0).getCell(0).getValue());
			assertEquals("cc", tuples.get(0).getCell(1).getValue());
			assertEquals("Rock", tuples.get(3).getCell(0).getValue());
			assertEquals("33", tuples.get(4).getCell(2).getValue());
			assertEquals("tt", tuples.get(7).getCell(1).getValue());
			
			int[] arrs = new int[]{1,2,3,10,7};
			tuples = csvr.getDataFromColumnsLimited(arrs, 3);
			assertEquals("bb", tuples.get(0).getCell(0).getValue());
			assertEquals("cc", tuples.get(0).getCell(1).getValue());
			assertEquals("789", tuples.get(2).getCell(0).getValue());
			assertEquals("ee", tuples.get(3).getCell(1).getValue());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
