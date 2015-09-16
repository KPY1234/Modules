package modules.ml.core;

import java.io.Serializable;
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
	
	private void fill(int columns){
		for(int i=records.size();i<columns;i++)
			records.add("null");
	}
	
	public Vector<String> getRecords(){
		return records;
	}
	
	public void removeColumn(int index){
		records.remove(index);
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
		String line = "1,2.21,,aa";
		Instance inst = new Instance(line,",");
		System.out.println(inst);
	}
	
}
