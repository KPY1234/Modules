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
	
		String[] columns = new String[]{"Name, Sex, Phone, , Address"};
		Attributes att2 = new Attributes(columns);
		assertArrayEquals(columns, att2.getAttNames().toArray());
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
