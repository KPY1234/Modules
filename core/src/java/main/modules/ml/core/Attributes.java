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
		if(isLabeled()){
			try{
				types.set(labelIndex, "nominal");
			}catch(IndexOutOfBoundsException ioobe){
				System.err.println("Label索引設定超出範圍: label index = "+labelIndex+", column size = "+size());
			}
		}
	}
	
	private boolean isLabeled(){
		if(labelIndex>=0)
			return true;
		else
			return false;
	}
	
	public void setLabelIndex(int labelIndex){
		this.labelIndex = labelIndex;
		if(isLabeled()){
			try{
				types.set(labelIndex, "nominal");
			}catch(IndexOutOfBoundsException ioobe){
				System.err.println("Label索引設定超出範圍: label index = "+labelIndex+", column size = "+size());
			}
		}
	}

	public void setAttName(String[] colNames){
		names.clear();
		types.clear();
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
		String type = types.get(attIndex);
		if(type.equals("numeric"))
			bounds.remove(attIndex);
		else
			nominals.remove(attIndex);
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
			for(int columnIndex=0;columnIndex<size();columnIndex++){
				
				String record = inst.get(columnIndex);
				
				if(isStringEqualNull(record))
					continue;

				String dataType = getColumnType(columnIndex);
				if(dataType.equals("numeric")){
					double recordNumeric = Double.parseDouble(record);
					refreshNumericBoundary(columnIndex, recordNumeric);
				}
				else
					refreshNominalBoundary(columnIndex, record);
				
			}
		}
	}
	
	private boolean isStringEqualNull(String str){
		
		if(str.equals("NULL")||str.equals("null"))
			return true;
		else
			return false;
	}
	
	private void refreshNumericBoundary(int index, double number){
		
		if(bounds.containsKey(index))
			bounds.get(index).checkBoundary(number);
		else{
			AttributeBoundary ab = new AttributeBoundary();
			ab.checkBoundary(number);
			bounds.put(index, ab);
		}
	}
	
	private void refreshNominalBoundary(int index, String nominal){
		
		if(nominals.containsKey(index)){
			ArrayList<String> set = nominals.get(index);
			if(!set.contains(nominal))
				set.add(nominal);
		}
		else{
			ArrayList<String> set = new ArrayList<String>();
			set.add(nominal);
			nominals.put(index, set);
		}
	}
	
	public ArrayList<String> getLabelSet(){
		try{
			return nominals.get(labelIndex);
		}catch(ArrayIndexOutOfBoundsException aioobe){
			System.err.println("無法取得Label集合: 超出索引範圍");
			return new ArrayList<String>();
		}
	}
	
	public String toString(){
		
		String str = "att: "+names.toString()+"\n";
		str +="type: "+types.toString()+"\n";
		str +="bounds: "+bounds.toString()+"\n";
		str +="nominals: "+nominals.toString()+"\n";
		str += "label index: "+labelIndex;
		return str;
	}
	
	public static void main(String[] args) {
		
		String[] attNames = new String[]{"f1","f2","f3","f4","f5","label"};
		String line1 = "1,2.21,aa1, ,ee";
		String line2 = "2,2.31,aa, ,ee, qq, 33";
		String line3 = "3,7.21,aa,7,qe";
		String line4 = "4,5.2,aa, ,uuu, 22";
		
		Attributes atts = new Attributes(attNames, -1);
		
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
