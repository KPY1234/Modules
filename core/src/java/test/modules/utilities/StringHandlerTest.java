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
		assertEquals(0, StringHandler.findHead("AWSnnns","AWS"));	//�r����
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS"));	//�r����
		assertEquals(5, StringHandler.findHead("abcqw(AWS)nnns","\\(.*\\)")); //���W�����
		assertEquals(9, StringHandler.findHead("qq,ww,ee,\"aa\"\",\"\"cc\"","\".*\"")); //���W�����
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS", 2));	//�r����
		assertEquals(-1, StringHandler.findHead("abcqwAWSnnns","AWS", 6));	//�r����
		
	}
	
	@Test
	public void findHeadEndTest() {
		assertEquals(7, StringHandler.findEnd("abcqwAWS","AWS"));	//�r����
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS"));	//�r����
		assertEquals(9, StringHandler.findEnd("abcqw(AWS)nnns","\\(.*\\)")); //���W�����
		assertEquals(19, StringHandler.findEnd("qq,ww,ee,\"aa\"\",\"\"cc\"","\".*\"")); //���W�����
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS", 2));	//�r����
		assertEquals(-1, StringHandler.findEnd("abcqwAWSnnns","AWS", 6));	//�r����
		
	}
	
	@Test
	public void appearCountTest() {
		assertEquals(4, StringHandler.appearCount("AABB,CCSS,GGHH,AA","A"));
		assertEquals(2, StringHandler.appearCount("AABB,CCSS,GGHH,AA","AA"));
		assertEquals(1, StringHandler.appearCount("AABB,CCSS,GGHH,AA","CC"));
		assertEquals(3, StringHandler.appearCount("AABB,CCSS,GGHH,AA",","));
	}

}
