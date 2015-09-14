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
		assertEquals(0, StringHandler.findHead("AWSnnns","AWS"));	//�r����
		assertEquals(1, StringHandler.findHead("\nAWSnnns","AWS"));	//�r����
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS"));	//�r����
		assertEquals(0, StringHandler.findHead("<abc>\nq<w>\nAW<Snn>ns","<.*>"));	//���W�����A�I�촫��h�L�k�s��̤j���
		assertEquals(-1, StringHandler.findHead("<ab\nc>\nq\nAWns","<.*>"));	//���W�����A�I�촫��h�L�k�s��̤j���
		assertEquals(0, StringHandler.findHead("<abc>\tq<w>\nAW<Snn>ns","<[^<]*>"));	//���W�����A�j���I��Ĥ@���ǰt�N�^��
		assertEquals(5, StringHandler.findHead("abcqw(AWS)nnns","\\(.*\\)")); //���W�����
		assertEquals(9, StringHandler.findHead("qq,ww,ee,\"aa\"\",\"\"cc\"","\".*\"")); //���W�����
		assertEquals(5, StringHandler.findHead("abcqwAWSnnns","AWS", 2));	//�r����
		assertEquals(-1, StringHandler.findHead("abcqwAWSnnns","AWS", 6));	//�r����
	}
	
	@Test
	public void findHeadEndTest() {
		assertEquals(7, StringHandler.findEnd("abcqwAWS","AWS"));	//�r����
		assertEquals(8, StringHandler.findEnd("abcqw\nAWS","AWS"));	//�r����
		assertEquals(7, StringHandler.findEnd("abcqwAWSnnns","AWS"));	//�r����
		assertEquals(4, StringHandler.findEnd("<abc>\nq<w>\nAW<Snn>ns","<.*>"));	//���W�����A�I�촫��h�L�k�s��̤j���
		assertEquals(4, StringHandler.findEnd("<abc>\tq<w>\nAW<Snn>ns","<[^>]*>"));	//���W�����A�j���I��Ĥ@���ǰt�N�^��
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
