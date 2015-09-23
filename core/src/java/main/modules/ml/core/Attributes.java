package modules.ml.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import modules.utilities.string.StringHandler;

public class Attributes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> names; 
	private ArrayList<String> types; 
	
	private HashMap<Integer, AttributeBoundary> bounds;
	private HashMap<Integer, ArrayList<String>> nominals;
	
	private int labelIndex = -1;	//若資料集無label(非監督式學習)，則label index為-1

	public Attributes(){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		bounds = new HashMap<Integer, AttributeBoundary>();
		nominals = new HashMap<Integer, ArrayList<String>>();
	}
	
	public Attributes(int labelIndex){
		this.labelIndex = labelIndex;
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		bounds = new HashMap<Integer, AttributeBoundary>();
		nominals = new HashMap<Integer, ArrayList<String>>();
	}
	
	public Attributes(String[] colNames){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		bounds = new HashMap<Integer, AttributeBoundary>();
		nominals = new HashMap<Integer, ArrayList<String>>();
		for(int i=0;i<colNames.length;i++){
			names.add(colNames[i]);
			types.add("numeric");
		}
			
	}
	
	public Attributes(String[] colNames, int labelIndex){
		this.labelIndex = labelIndex;
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		bounds = new HashMap<Integer, AttributeBoundary>();
		nominals = new HashMap<Integer, ArrayList<String>>();
		for(int i=0;i<colNames.length;i++){
			names.add(colNames[i]);
			types.add("numeric");
		}
			
	}
	
	public void setLabelIndex(int labelIndex){
		this.labelIndex = labelIndex;
	}

	public void setAttName(String[] colNames){
		names.clear();
		for(int i=0;i<colNames.length;i++){
			names.add(colNames[i]);
			types.add("numeric");
		}
			
	}
	
	public void addAttName(String name){
		names.add(name);
		types.add("numeric");
	}
	
	public void setColumnType(int columnIndex, String type){
		types.set(columnIndex, type);
	}
	
	/**
	 * 檢查資料欄位的型態
	 * @param inst 單筆資料實例
	 */
	public void checkTypes(int count, Instance inst){
		
		if(labelIndex>0)   //為監督式學習資料集
			types.set(labelIndex, "nominal");
		
		Vector<String> vec = inst.getRecords();
		for(int i=0;i<size();i++){
			try{
				String value = vec.elementAt(i).trim();
				if(value.equals("null"))
					continue;
				if(!StringHandler.isNumeric(value)){
					types.set(i, "nominal");
				}
			}catch(ArrayIndexOutOfBoundsException aioobe){
				System.err.println("inst#"+count+" -> instance size:"+ vec.size()+" < "+"attributes size:"+size());
			}
		}
	}
	
	public ArrayList<String> getAttNames(){
		return names;
	}
	
	public String getColumnType(int columnIndex){
		return types.get(columnIndex);
	}
	
	public ArrayList<String> getTypes(){
		return types;
	}
	
	public void remove(int attIndex){
		names.remove(attIndex);
		types.remove(attIndex);
	}
	
	public int getLabelIndex(){
		return labelIndex;
	}
	
	public int size(){
		return names.size();
	}
	
	public HashMap<Integer, AttributeBoundary> getBounds(){
		return bounds;
	}
	
	public HashMap<Integer, ArrayList<String>> getNominals(){
		return nominals;
	}
	
	public void checkAttributesBoundary(ArrayList<Instance> insts){
		
		for(int i=0;i<insts.size();i++){
			Instance inst = insts.get(i);
			for(int columnIndex=0;columnIndex<inst.size();columnIndex++){
				String dataType = getColumnType(columnIndex);
				if(dataType.equals("numeric")){
					double record = Double.parseDouble(inst.get(columnIndex));
					refreshNumericBoundary(columnIndex, record);
				}
				else{
					String record = inst.get(columnIndex);
					refreshNominalBoundary(columnIndex, record);
				}
			}
		}
	}
	
	private void refreshNumericBoundary(int index, double number){
		bounds.get(index).checkBoundary(number);
	}
	
	private void refreshNominalBoundary(int index, String nominal){
		
		ArrayList<String> set = nominals.get(index);
		if(!set.contains(nominal))
			set.add(nominal);
	}
	
	public String toString(){
		
		String str = "att: "+names.toString()+"\n";
		str +="type: "+types.toString()+"\n";
		str +="bounds: "+bounds.toString()+"\n";
		str +="nominals: "+nominals.toString();
		return str;
	}
	
	public static void main(String[] args) {
		
		String[] attNames = new String[]{"f1","f2","f3","f4","f5","label"};
		String line1 = "1,2.21,aa1, ,ee";
		String line2 = "2,2.31,aa, ,ee, qq, 33";
		String line3 = "3,7.21,aa,7,qe";
		String line4 = "4,5.2,aa, ,uuu, 22";
		
		Attributes atts = new Attributes(attNames, 5);
		
		ArrayList<Instance> insts = new ArrayList<Instance>();
		insts.add(new Instance(line1,","));
		insts.add(new Instance(line2,","));
		insts.add(new Instance(line3,","));
		insts.add(new Instance(line4,","));
		
		for(int i=0;i<insts.size();i++)
			atts.checkTypes(i, insts.get(i));
			
		
		atts.checkAttributesBoundary(insts);
		
		System.out.println(atts);
	}
	
}
