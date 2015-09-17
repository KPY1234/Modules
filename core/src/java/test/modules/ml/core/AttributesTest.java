package modules.ml.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class AttributesTest {

	
	@Test
	public void constuctorTest(){
		Attributes att1 = new Attributes();
		assertEquals(0, att1.getAttNames().size());
		assertEquals(-1, att1.getLabelIndex());
		
		Attributes att2 = new Attributes(7);
		assertEquals(0, att2.getAttNames().size());
		assertEquals(7, att2.getLabelIndex());
	
		String[] columns = new String[]{"Name, Sex, Phone, , Address"};
		Attributes att3 = new Attributes(columns);
		assertArrayEquals(columns, att3.getAttNames().toArray());
		
		Attributes att4 = new Attributes(columns, 7);
		assertEquals(7, att4.getLabelIndex());
		
		
	}
	
	@Test
	public void setLabelIndexTest(){
		Attributes att = new Attributes();
		assertEquals(-1, att.getLabelIndex());
		att.setLabelIndex(5);
		assertEquals(5, att.getLabelIndex());
	}
	
	@Test
	public void setAttNameTest(){
		
		Attributes att = new Attributes();
		String[] columns1 = new String[]{"Name, Sex, Phone, , Address"};
		att.setAttName(columns1);
		assertArrayEquals(columns1, att.getAttNames().toArray());
		
		String[] columns2 = new String[]{"Name, Sex, Phone, , Address, Height"};
		att.setAttName(columns2);
		assertArrayEquals(columns2, att.getAttNames().toArray());
	}
	
	@Test
	public void addAttNameTest(){
		
		Attributes att = new Attributes();
		ArrayList<String> attlist = new ArrayList<String>();
		att.addAttName("Name");
		attlist.add("Name");
		assertEquals(attlist.toString(), att.getAttNames().toString());
		att.addAttName("Sex");
		attlist.add("Sex");
		assertEquals(attlist.toString(), att.getAttNames().toString());
		att.addAttName("Phone");
		attlist.add("Phone");
		assertEquals(attlist.toString(), att.getAttNames().toString());
	}
	
	
	@Test
	public void checkTypesTest(){
		
		
		
		
	}
	
	
	
	
}
