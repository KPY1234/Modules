package modules.reader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import modules.reader.CSVReader;


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
	
	
	

}
