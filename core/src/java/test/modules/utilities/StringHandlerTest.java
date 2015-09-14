package modules.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringHandlerTest {
	
	@Test
	public void deleteTest() {
		
		assertEquals("ABCDHIJK", StringHandler.delete("ABCDefgHIJK", 4, 6));
		assertEquals("ABCDHIJK", StringHandler.delete("ABCDqqefgqqHIJK", 4, 10));
	}
	
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
		assertEquals(1, StringHandler.findHead("\nAWSnnns","AWS"));	//字串比對
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS"));	//字串比對
		assertEquals(0, StringHandler.findHead("<abc>\nq<w>\nAW<Snn>ns","<.*>"));	//正規式比對，碰到換行則無法連續最大比對
		assertEquals(-1, StringHandler.findHead("<ab\nc>\nq\nAWns","<.*>"));	//正規式比對，碰到換行則無法連續最大比對
		assertEquals(0, StringHandler.findHead("<abc>\tq<w>\nAW<Snn>ns","<[^<]*>"));	//正規式比對，強迫碰到第一次匹配就回傳
		assertEquals(5, StringHandler.findHead("abcqw(AWS)nnns","\\(.*\\)")); //正規式比對
		assertEquals(9, StringHandler.findHead("qq,ww,ee,\"aa\"\",\"\"cc\"","\".*\"")); //正規式比對
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS", 2));	//字串比對
		assertEquals(-1, StringHandler.findHead("abcqwAWSnnns","AWS", 6));	//字串比對
	}
	
	@Test
	public void findHeadEndTest() {
		assertEquals(7, StringHandler.findEnd("abcqwAWS","AWS"));	//字串比對
		assertEquals(8, StringHandler.findEnd("abcqw\nAWS","AWS"));	//字串比對
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS"));	//字串比對
		assertEquals(4, StringHandler.findEnd("<abc>\nq<w>\nAW<Snn>ns","<.*>"));	//正規式比對，碰到換行則無法連續最大比對
		assertEquals(4, StringHandler.findEnd("<abc>\tq<w>\nAW<Snn>ns","<[^>]*>"));	//正規式比對，強迫碰到第一次匹配就回傳
		assertEquals(9, StringHandler.findEnd("abcqw(AWS)nnns","\\(.*\\)")); //正規式比對
		assertEquals(19, StringHandler.findEnd("qq,ww,ee,\"aa\"\",\"\"cc\"","\".*\"")); //正規式比對
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS", 2));	//字串比對
		assertEquals(-1, StringHandler.findEnd("abcqwAWSnnns","AWS", 6));	//字串比對
		
	}
	
	@Test
	public void appearCountTest() {
		assertEquals(4, StringHandler.appearCount("AABB,CCSS,GGHH,AA","A"));
		assertEquals(2, StringHandler.appearCount("AABB,CCSS,GGHH,AA","AA"));
		assertEquals(1, StringHandler.appearCount("AABB,CCSS,GGHH,AA","CC"));
		assertEquals(3, StringHandler.appearCount("AABB,CCSS,GGHH,AA",","));
	}

}
