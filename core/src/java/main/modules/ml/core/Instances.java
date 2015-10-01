package modules.ml.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Instances implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String dataName;
	Attributes atts;
	ArrayList<Instance> insts;
	
	public Instances(Attributes atts){
		this.atts = atts;
		insts = new ArrayList<Instance>();
	}
	
	

	public void addInstance(Instance inst) throws AttributesNotSetException{
	
		if(!hasSetAttributes()){
			String message = "例外發生: 未設定屬性(Attributes) ";
			throw new AttributesNotSetException(message);
		}
		
		if(inst.size()<atts.size())
			inst = fillNullValue(inst, atts.size());
		
		insts.add(inst);
		int instNum = insts.size()-1;
		atts.checkTypes(instNum, inst);
		
	}
	
	
	private Instance fillNullValue(Instance inst, int attSize){
		for(int i=inst.size();i<attSize;i++)
			inst.add("null");
		return inst;
	}
	
	private boolean hasSetAttributes(){
		if(atts.size()==0)
			return false;
		else
			return true;
	}
	
	public void checkAttributesBoundry(){
		atts.checkAttributesBoundary(insts);
	}
	
	public void setDataName(String dataName){
		this.dataName = dataName;
	}
	
	public String getDataName(){
		return dataName;
	}
	
	public void setAttributes(Attributes atts){
		this.atts = atts;
	}
	
	public void removeColumn(int columnIndex){
		
		atts.remove(columnIndex);
		
		// 如果刪除的欄位為Label欄位，則重設Label Index為-1
		if(columnIndex == atts.getLabelIndex())
			atts.setLabelIndex(-1);
		
		// 如果刪除的欄位為大於Label欄位，則Label Index為減1
		if(atts.getLabelIndex() > columnIndex)
			atts.setLabelIndex(atts.getLabelIndex()-1);
		
		for(int i=0;i<insts.size();i++)
			insts.get(i).removeColumn(columnIndex);
		
	}
	
	public void clearData(){
		insts.clear();
	}
	
	public void removeColumn(ArrayList<Integer> columnIndexs){
		
		Object[] indexs = columnIndexs.toArray();
		Arrays.sort(indexs);
		
		for(int i=indexs.length-1;i>=0;i--){
			int index = (int) indexs[i];
			removeColumn(index);
		}
		
	}
	
	public Attributes getAtts(){
		return atts;
	}
	
	public ArrayList<String> getLabelSet(){
		return atts.getLabelSet();
	}
	
	public int getLabelIndex(){
		return atts.getLabelIndex();
	}
	
	public Instance get(int index){
		return insts.get(index);
	}
	
	public int size(){
		return insts.size();
	}
	
	public String toString(){
		
		String str = "attributes: "+atts.toString();
	
		str += "\n@data";
		str += "\n";
		
		for(int i=0;i<insts.size();i++)
			str += insts.get(i).toString()+"\n";
		
		return str;
	}
	
	public static void main(String[] args) {
		
		String[] columns = new String[]{"f1", "f2", "f3", "f4", "Label"};
		
		
		Attributes atts = new Attributes(columns, 4);
		Instances insts = new Instances(atts);
		try {
			insts.addInstance(new Instance("1,2.21,aa1, ,ee",","));
			insts.addInstance(new Instance("2,2.31,aa",","));
			insts.addInstance(new Instance("3,7.21,aa,7,qe",","));
			insts.addInstance(new Instance("4,5.2,aaaa, ,uuu",","));
			
		} catch (AttributesNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		insts.checkAttributesBoundry();
		
		System.out.println(insts);
		
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		
		indexs.add(2);
		indexs.add(1);
		
		
		insts.removeColumn(indexs);
//		insts.removeColumn(0);
//		insts.removeColumn(1);
		
		System.out.println(insts);
		
		
		insts.removeColumn(0);
		System.out.println(insts);

	}
	
}
