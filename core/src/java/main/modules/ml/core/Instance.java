package modules.ml.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Vector;

public class Instance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Vector<String> records;
	
	public Instance(){
		records = new Vector<String>();
	}
	
	public Instance(String line, String delimit){
		
		records = new Vector<String>();
		String[] lineSplit = line.split(delimit);
		
		for(int i=0;i<lineSplit.length;i++){
			String value = lineSplit[i].trim();
			if(value.isEmpty())
				records.add("null");
			else
				records.add(value);
		}
	}
	
	public Vector<String> getRecords(){
		return records;
	}
	
	public void removeColumn(int index){
		records.remove(index);
	}
	
	public void removeColumns(int[] indexs){
		Arrays.sort(indexs);
		for(int i=indexs.length-1;i>=0;i--)
			removeColumn(indexs[i]);
	}
	
	public void add(String value){
		records.add(value);
	}
	
	public String get(int index){
		return records.elementAt(index);
	}
	
	public int size(){
		return records.size(); 
	}
	
	public String toString(){
		return records.toString();
	}
	
	public static void main(String[] args) {
		String line = "1,2.21,9,4,aa,,";
		Instance inst = new Instance(line,",");
		System.out.println(inst);
	
		int[] indices = new int[]{1,3,2};
		inst.removeColumns(indices);
		System.out.println(inst);
	}
}
