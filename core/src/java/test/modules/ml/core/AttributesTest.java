package modules.ml.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import modules.utilities.ArrayHandler;

public class AttributesTest {

	
	@Test
	public void constuctorTest(){
		Attributes att1 = new Attributes();
		assertEquals(0, att1.getAttNames().size());
		assertEquals(-1, att1.getLabelIndex());
		
		Attributes att2 = new Attributes(7);
		assertEquals(0, att2.getAttNames().size());
		assertEquals(7, att2.getLabelIndex());
	
		String[] columns = new String[]{"Name", "Sex", "Phone", "", "Address"};
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
		String[] columns1 = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		att.setAttName(columns1);
		assertArrayEquals(columns1, att.getAttNames().toArray());
		
		String[] columns2 = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
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
	public void setColumnTypeTest(){
		
		Attributes att = new Attributes();
		String[] columns = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		att.setAttName(columns);
		att.setColumnType(0, "nominal");
		assertEquals("nominal", att.getColumnType(0));
		att.setColumnType(2, "numeric");
		assertEquals("numeric", att.getColumnType(2));
	}
	
	@Test
	public void checkTypesTest(){
		
		Attributes att = new Attributes();
		
		att.checkTypes(0,new Instance("aa,bb,,1,9,,4", ","));
		att.checkTypes(1,new Instance("aa,bb,,1,9,,4", ","));
		
		
	}
	
	@Test
	public void labelIndexTest(){
		
		Attributes att = new Attributes();
		att.setLabelIndex(2);
		assertEquals(2, att.getLabelIndex());
	}
	
	
	@Test
	public void removeTest(){
		
		Attributes att = new Attributes();
		String[] columns = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		att.setAttName(columns);
		att.remove(0);
		assertArrayEquals(ArrayHandler.remove(columns, 0), att.getAttNames().toArray());
	}
	
}
