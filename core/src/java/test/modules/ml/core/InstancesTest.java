package modules.ml.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class InstancesTest {

	@Test
	public void constuctorTest(){
		
		Attributes atts = new Attributes(new String[]{"F1","F2","Label"});
		Instances insts = new Instances(atts);
		assertEquals(0, insts.size());
		
	}
	
	@Test
	public void addInstanceTest(){
		
		Attributes atts = new Attributes(new String[]{"F1","F2","Label"});
		Instances insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,A",","));
			insts.addInstance(new Instance("2,3,B",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		assertEquals(2, insts.size());
		assertEquals("1", insts.get(0).get(0));
	}
	
	@Test
	public void checkAttributesBoundryTest(){
		
		
		Attributes atts = new Attributes(new String[]{"F1","F2","F3","Label"});
		Instances insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,,A",","));
			insts.addInstance(new Instance("2,3,CC,B",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		insts.checkAttributesBoundry();
		
		assertEquals("numeric", insts.getAtts().getTypes().get(0));
		assertEquals("numeric", insts.getAtts().getTypes().get(1));
		assertEquals("nominal", insts.getAtts().getTypes().get(2));
		assertEquals("nominal", insts.getAtts().getTypes().get(3));
	}
	
	@Test
	public void removeColumnTest(){
		
		Attributes atts = new Attributes(new String[]{"F1","F2","F3","Label"});
		Instances insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,,A",","));
			insts.addInstance(new Instance("2,3,CC,B",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		insts.checkAttributesBoundry();
		
		insts.removeColumn(0);
		assertEquals("2", insts.get(0).get(0));
		insts.removeColumn(0);
		assertEquals("null", insts.get(0).get(0));
		
		
		atts = new Attributes(new String[]{"F1","F2","F3","F4","F5","Label"}, 5);
		insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,,,5,A",","));
			insts.addInstance(new Instance("2,3,CC,B",","));
			insts.addInstance(new Instance("2,3,CC,B,,",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		insts.checkAttributesBoundry();
		
		insts.removeColumn(0);
		assertEquals(4, insts.getLabelIndex());

		insts.removeColumn(4);
		assertEquals(-1, insts.getLabelIndex());
		
		
		
		atts = new Attributes(new String[]{"F1","F2","F3","F4","F5","Label"}, 5);
		insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,,,5,A",","));
			insts.addInstance(new Instance("2,3,CC,B",","));
			insts.addInstance(new Instance("2,3,CC,B,,",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		insts.checkAttributesBoundry();
		
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		indices.add(3);
		indices.add(0);
		insts.removeColumn(indices);
		assertEquals("2", insts.get(0).get(0));
		assertEquals("5", insts.get(0).get(2));
		
	}
	
	@Test
	public void clearDataTest(){
		
		Attributes atts = new Attributes(new String[]{"F1","F2","F3","Label"});
		Instances insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2,,A",","));
			insts.addInstance(new Instance("2,3,CC,B",","));
		} catch (AttributesNotSetException e) {
			e.printStackTrace();
		}
		insts.checkAttributesBoundry();
		insts.clearData();
		assertEquals(0, insts.size());
		
	}
	
	@Test
	public void getAttsTest(){
		
		Attributes atts = new Attributes(new String[]{"F1","F2","F3","Label"});
		Instances insts = new Instances(atts);
		assertEquals(atts, insts.getAtts());
		
	}

	
	
	
	
}
