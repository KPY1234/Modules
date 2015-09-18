package modules.ml.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import modules.utilities.string.StringHandler;

public class Attributes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> names; 
	private ArrayList<String> types; 
	private int labelIndex = -1;	//若資料集無label(非監督式學習)，則label index為-1

	public Attributes(){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	
	public Attributes(int labelIndex){
		this.labelIndex = labelIndex;
		names = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	
	public Attributes(String[] colNames){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		for(int i=0;i<colNames.length;i++){
			names.add(colNames[i]);
			types.add("numeric");
		}
			
	}
	
	public Attributes(String[] colNames, int labelIndex){
		this.labelIndex = labelIndex;
		names = new ArrayList<String>();
		types = new ArrayList<String>();
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
	
	public String toString(){
		
		String str = "att: "+names.toString()+"\n";
		str +="type: "+types.toString();
		return str;
	}
	
	public static void main(String[] args) {
		
		String[] attNames = new String[]{"f1","f2","f3","f4","f5","label"};
		String line1 = "1,2.21,aa1, ,ee";
		String line2 = "2,2.31,aa, ,ee, qq, 33";
		String line3 = "3,7.21,aa,7,qe";
		String line4 = "4,5.2,aa, ,uuu, 22";
		Attributes atts = new Attributes(attNames, 5);
		atts.checkTypes(0, new Instance(line1,","));
		atts.checkTypes(1,new Instance(line2,","));
		atts.checkTypes(2,new Instance(line3,","));
		atts.checkTypes(3,new Instance(line4,","));
		System.out.println(atts);
	}
	
}
