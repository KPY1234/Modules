package modules.reader;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import modules.reader.CSVReader;


public class CSVReaderTest {
	
	
	@Test
	public void readTest() {
	
		CSVReader csvr = new CSVReader();
		
		try {
			csvr.read("./TestData/CSVReaderTest.csv", 1);
			String correctContent = "aa,bb,cc\n11,22,33,44\n\nJack,Rock\n12345,789,"
					+ "4,33\nqq,ww,ee\n";
			assertEquals(correctContent, csvr.getContent());
			
			csvr.read("./TestData/CSVReaderTest.csv", 2);
			correctContent = "11,22,33,44\n\nJack,Rock\n12345,789,4,33\n"
					+ "qq,ww,ee\n";
			assertEquals(correctContent, csvr.getContent());
			
			csvr.read("./TestData/CSVReaderTest.csv", 2, 4);
			correctContent = "11,22,33,44\n\nJack,Rock\n";
			assertEquals(correctContent, csvr.getContent());
			
			csvr.read("./TestData/CSVReaderTest.csv", 2, 5);
			correctContent = "11,22,33,44\n\nJack,Rock\n12345,789,4,33\n";
			assertEquals(correctContent, csvr.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
