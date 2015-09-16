package modules.ml.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import modules.utilities.StringHandler;

public class Attributes implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> names; 
	ArrayList<String> types; 

	public Attributes(){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	
	public Attributes(String[] colNames){
		names = new ArrayList<String>();
		types = new ArrayList<String>();
		for(int i=0;i<colNames.length;i++)
			names.add(colNames[i]);
	}

	public void setAttName(String[] colNames){
		names.clear();
		for(int i=0;i<colNames.length;i++)
			names.add(colNames[i]);
	}
	
	public void addAttName(String name){
		names.add(name);
	}
	
	/**
	 * 檢查資料欄位的型態
	 * @param inst 單筆資料實例
	 * @param labelIndex 監督式學習的label位置 
	 */
	public void checkTypes(Instance inst, int labelIndex){
		
		Vector<String> vec = inst.getRecords();
		for(int i=0;i<vec.size();i++){
			if(i+1>types.size())
				types.add("numeric");
			if(i==labelIndex)
				types.set(i, "nominal");
	
			String value = vec.elementAt(i).trim();
			if(value.isEmpty())
				continue;
			if(!StringHandler.isNumeric(value)){
				types.set(i, "nominal");
			}
		}
	}
	
	public ArrayList<String> getAttNames(){
		return names;
	}
	
	public ArrayList<String> getTypes(){
		return types;
	}
	
	public void remove(int attIndex){
		names.remove(attIndex);
		types.remove(attIndex);
	}
	
	public String toString(){
		
		String str = "att: "+names.toString()+"\n";
		str +="type: "+types.toString();
		return str;
	}
	
	public static void main(String[] args) {
		
		String[] attNames = new String[]{"f1","f2","f3","f4","f5"};
		String line1 = "1,2.21,aa1, ,ee";
		String line2 = "2,2.31,aa, ,ee";
		String line3 = "3,7.21,aa,7,qe";
		String line4 = "4,5.2,aa, ,uuu";
		Attributes atts = new Attributes(attNames);
		atts.checkTypes(new Instance(line1,","),0);
		atts.checkTypes(new Instance(line2,","),0);
		atts.checkTypes(new Instance(line3,","),0);
		atts.checkTypes(new Instance(line4,","),0);
		System.out.println(atts);
	}
	
}
