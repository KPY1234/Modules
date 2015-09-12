package modules.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringHandlerTest {
	
	@Test
	public void splitByTokensTest() {
		String[] splitStrs = StringHandler.splitByTokens("aa,bb'ww\"yy", ",'\"");
		String correctStr = "";
		for(int i=0;i<splitStrs.length;i++)
			correctStr += splitStrs[i]+"\t";
		assertEquals("aa\tbb\tww\tyy\t", correctStr);
		assertEquals(4, splitStrs.length);
		
		splitStrs = StringHandler.splitByTokens("aa%bb@w^w!y&y(nn)zz", "%@^!&()");
		correctStr = "";
		for(int i=0;i<splitStrs.length;i++)
			correctStr += splitStrs[i]+"\t";
		assertEquals("aa\tbb\tw\tw\ty\ty\tnn\tzz\t", correctStr);
		assertEquals(8, splitStrs.length);
		
		splitStrs = StringHandler.splitByTokens("aa[bb]w|w`y/y\\nn+zz", "[]|!`/\\+");
		correctStr = "";
		for(int i=0;i<splitStrs.length;i++)
			correctStr += splitStrs[i]+"\t";
		assertEquals("aa\tbb\tw\tw\ty\ty\tnn\tzz\t", correctStr);
		assertEquals(8, splitStrs.length);
		
	}
	
	@Test
	public void findHeadTest() {
		assertEquals(0, StringHandler.findHead("AWSnnns","AWS"));	//字串比對
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS"));	//字串比對
		assertEquals(5, StringHandler.findHead("abcqw(AWS)nnns","\\(.*\\)")); //正規式比對
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS", 2));	//字串比對
		assertEquals(-1, StringHandler.findHead("abcqwAWSnnns","AWS", 6));	//字串比對
		
	}
	
	@Test
	public void findHeadEnd() {
		assertEquals(7, StringHandler.findEnd("abcqwAWS","AWS"));	//字串比對
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS"));	//字串比對
		assertEquals(9, StringHandler.findEnd("abcqw(AWS)nnns","\\(.*\\)")); //正規式比對
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS", 2));	//字串比對
		assertEquals(-1, StringHandler.findEnd("abcqwAWSnnns","AWS", 6));	//字串比對
		
	}
	
	

}
