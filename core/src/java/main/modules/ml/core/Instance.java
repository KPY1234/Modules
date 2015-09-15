package modules.ml.core;

import java.io.Serializable;
import java.util.Vector;

public class Instance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Vector<String> vec;
	
	public Instance(){
		vec = new Vector<String>();
	}
	
	public Instance(String line, String delimit, int columns){
		
		vec = new Vector<String>();
		
		String[] lineSplit = line.split(delimit);
		for(int i=0;i<lineSplit.length;i++){
			String value = lineSplit[i].trim().replaceAll(" ", "");
			if(value.isEmpty())
				vec.add("null");
			else
				vec.add(value);
		}
//		System.out.println(vec);
		
		fill(columns);
	}
	
	private void fill(int columns){
		for(int i=vec.size();i<columns;i++)
			vec.add("null");
	}
	
	public Vector<String> getVec(){
		return vec;
	}
	
	public void removeColumn(int index){
		vec.remove(index);
	}
	
	public void add(String value){
		vec.add(value);
	}
	
	public String get(int index){
		return vec.elementAt(index);
	}
	
	public int size(){
		return vec.size(); 
	}
	
	public String toString(){
		return vec.toString();
	}
	
	public static void main(String[] args) {
		String line = "1,2.21,,aa";
		Instance inst = new Instance(line,",",5);
		System.out.println(inst);
	}
	
}
