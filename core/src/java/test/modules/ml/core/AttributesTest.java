package modules.ml.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import modules.utilities.ArrayHandler;

public class AttributesTest {

	
	@Test
	public void constuctorTest(){
		
		String[] columns = new String[]{"Name", "Sex", "Phone", "", "Address"};
		Attributes att1 = new Attributes(columns);
		assertArrayEquals(columns, att1.getAttNames().toArray());
		assertEquals(5, att1.getTypes().size());
		assertEquals("numeric", att1.getTypes().get(0));
		assertEquals("numeric", att1.getTypes().get(3));
		assertEquals(-1, att1.getLabelIndex());
		
		Attributes att2 = new Attributes(columns, 7);
		assertEquals(7, att2.getLabelIndex());
		assertEquals(5, att2.size());
		
		
		Attributes att3 = new Attributes(columns, 1);
		assertEquals(1, att3.getLabelIndex());
		assertEquals(5, att3.size());
		assertEquals("nominal", att3.getTypes().get(1));
	}
	
	@Test
	public void setLabelIndexTest(){
		Attributes att = new Attributes(new String[]{"Name", "Sex", "Phone", "", "Address"});
		assertEquals(-1, att.getLabelIndex());
		att.setLabelIndex(5);
		assertEquals(5, att.getLabelIndex());
		att.setLabelIndex(4);
		assertEquals(4, att.getLabelIndex());
		assertEquals("nominal", att.getTypes().get(4));
	}
	
	@Test
	public void setAttNameTest(){
		
		String[] columns1 = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		Attributes att = new Attributes(columns1);
		att.setAttName(columns1);
		assertArrayEquals(columns1, att.getAttNames().toArray());
		assertEquals(6, att.getTypes().size());
		
		String[] columns2 = new String[]{"Name", "Sex", "Age", "Phone", "", "Address","Hobby"};
		att.setAttName(columns2);
		assertArrayEquals(columns2, att.getAttNames().toArray());
		assertEquals(7, att.getTypes().size());
	}
	
	@Test
	public void addAttNameTest(){
		
		Attributes att = new Attributes(new String[]{});
		ArrayList<String> attlist = new ArrayList<String>();
		att.addAttName("Name");
		attlist.add("Name");
		assertEquals(attlist.toString(), att.getAttNames().toString());
		assertEquals("numeric", att.getTypes().get(0));
		att.addAttName("Sex");
		attlist.add("Sex");
		assertEquals(attlist.toString(), att.getAttNames().toString());
		assertEquals("numeric", att.getTypes().get(1));
		att.addAttName("Phone");
		attlist.add("Phone");
		assertEquals(attlist.toString(), att.getAttNames().toString());
	}
	
	@Test
	public void setColumnTypeTest(){
		
		String[] columns = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		Attributes att = new Attributes(columns);
		att.setAttName(columns);
		att.setColumnType(0, "nominal");
		assertEquals("nominal", att.getColumnType(0));
		att.setColumnType(2, "numeric");
		assertEquals("numeric", att.getColumnType(2));
	}
	
	@Test
	public void checkTypesTest(){
		
		String[] columnNames = new String[]{"f1","f2","f3","f4","f5","f6","f7"};
		Attributes att = new Attributes(columnNames);
	
		
		att.checkTypes(0,new Instance("aa,bb,,1,9,,4", ","));
		att.checkTypes(1,new Instance("aca,bb,,1.4,9,,4y", ","));
		assertEquals("nominal", att.getTypes().get(0));
		assertEquals("numeric", att.getTypes().get(2));
		assertEquals("numeric", att.getTypes().get(3));
		
	}
	
	@Test
	public void labelIndexTest(){
		
		Attributes att = new Attributes(new String[]{});
		att.setLabelIndex(2);
		assertEquals(2, att.getLabelIndex());
	}
	
	
	@Test
	public void removeTest(){
		
		String[] columns = new String[]{"Name", "Sex", "Age", "Phone", "", "Address"};
		Attributes att = new Attributes(columns);
		att.remove(0);
		assertArrayEquals(ArrayHandler.remove(columns, 0), att.getAttNames().toArray());
	}
	
}
