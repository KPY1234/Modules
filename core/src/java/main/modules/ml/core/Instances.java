package modules.ml.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import modules.utilities.Cloner;


public class Instances implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String dataName;
	Attributes atts;
	ArrayList<Instance> insts;
	
	HashMap<Integer, Double> maxNums;
	HashMap<Integer, Double> minNums;
	HashMap<Integer, ArrayList<String>> nominals;
	
	ArrayList<String> labels;
	
	
	
	public Instances(){
		atts = new Attributes();
		insts = new ArrayList<Instance>();
		maxNums = new HashMap<Integer, Double>();
		minNums = new HashMap<Integer, Double>();
		nominals = new HashMap<Integer, ArrayList<String>>();
		labels = new ArrayList<String>();
		
	}
	
	public Instances(Attributes atts){
		this.atts = atts;
		atts = new Attributes();
		insts = new ArrayList<Instance>();
		maxNums = new HashMap<Integer, Double>();
		minNums = new HashMap<Integer, Double>();
		nominals = new HashMap<Integer, ArrayList<String>>();
		labels = new ArrayList<String>();
		
	}
	
	
	public void addInstance(Instance inst){
		atts.checkTypes(insts.size(), inst);
		if(atts.getAttNames().size()==0){
			for(int i=0;i<inst.size();i++){
				if(i==atts.getLabelIndex())
					atts.getAttNames().add("Label");
				else
					atts.getAttNames().add("F"+(i+1));
			}
		}
			
		insts.add(inst);
		
		if(atts.getLabelIndex()!=-1){
			String label = inst.get(atts.getLabelIndex());
			if(!labels.contains(label))
				labels.add(label);
		}
	}
	

	public void checkBoundry(){
		
		ArrayList<String> attTypes = atts.getTypes();
		
		for(int i=0;i<insts.size();i++){
			Instance inst = insts.get(i);
			Vector<String> vec = inst.getRecords();
			
			for(int j=0;j<vec.size();j++){
				
				String value = vec.elementAt(j);
				if(value.trim().isEmpty())
					continue;
				if(attTypes.get(j).equals("numeric")){
					double numeric = Double.parseDouble(value);
					if(!maxNums.containsKey(j))
						maxNums.put(j, numeric);
					else{
						double max = maxNums.get(j);
						if(numeric>max)
							maxNums.put(j, numeric);
					}
					if(!minNums.containsKey(j))
						minNums.put(j, numeric);
					else{
						double min = minNums.get(j);
						if(numeric<min)
							minNums.put(j, numeric);
					}
					
	
				}
				else{
					if(!nominals.containsKey(j)){
						ArrayList<String> nominalValues = new ArrayList<String>();
						nominalValues.add(value);
						nominals.put(j, nominalValues);
					}
					else{
						ArrayList<String> nominalValues = nominals.get(j);
						if(!nominalValues.contains(value))
							nominalValues.add(value);
					}
				}
			}
		}
	}
	
	public void setDataName(String dataName){
		this.dataName = dataName;
	}
	
	public String getDataName(){
		return dataName;
	}
	
	public void removeColumn(int columnIndex){
		
		atts.remove(columnIndex);
		
		if(columnIndex == atts.getLabelIndex())
			atts.setLabelIndex(-1);
		if(atts.getLabelIndex() > columnIndex)
			atts.setLabelIndex(atts.getLabelIndex()-1);
			
		if(maxNums.containsKey(columnIndex))
			maxNums.remove(columnIndex);
		if(minNums.containsKey(columnIndex))
			minNums.remove(columnIndex);
		if(nominals.containsKey(columnIndex))
			nominals.remove(columnIndex);
		
		Object[] keys = maxNums.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			if((int)keys[i] > columnIndex){
				Double maxNum = maxNums.get(keys[i]);
				maxNums.remove(keys[i]);
				maxNums.put(((int)keys[i])-1, maxNum);
			}
		}
		
		keys = minNums.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			if((int)keys[i] > columnIndex){
				Double minNum = minNums.get(keys[i]);
				minNums.remove(keys[i]);
				minNums.put(((int)keys[i])-1, minNum);
			}
		}
		
		keys = nominals.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			if((int)keys[i] > columnIndex){
				ArrayList<String> nominalList = (ArrayList<String>) Cloner.clone(nominals.get(keys[i]));
				nominals.remove(keys[i]);
				nominals.put(((int)keys[i])-1, nominalList);
			}
		}
		
		for(int i=0;i<insts.size();i++){
			insts.get(i).removeColumn(columnIndex);
		}
	}
	
	public void clearData(){
		insts.clear();
	}
	
	public void removeColumn(ArrayList<Integer> columnIndexs){
		
		Object[] indexs = columnIndexs.toArray();
		Arrays.sort(indexs);
		
		int count=0;
		for(int i=0;i<indexs.length;i++){
			int index = (int)indexs[i] - count;
			removeColumn(index);
			count++;
		}
	}
	
	public Attributes getAtts(){
		return atts;
	}
	
	public ArrayList<String> getLabels(){
		return labels;
	}
	
	public HashMap<Integer, Double> getMaxNums(){
		return maxNums;
	}
	public HashMap<Integer, Double> getMinNums(){
		return minNums;
	}
	public HashMap<Integer, ArrayList<String>> getNominals(){
		return nominals;
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
		
		String str = "max boundry: "+getMaxNums().toString();
		str += "\nmin boundry: "+getMinNums().toString();
		str += "\nnominals boundry: "+getNominals().toString();
		
		if(atts.getLabelIndex()>-1)
			str += "\nlabels = "+getNominals().get(atts.getLabelIndex()).toString();
		
		str += "\n@data";
		str += "\n";
		
		for(int i=0;i<insts.size();i++){
			str += insts.get(i).toString()+"\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
		Instances insts = new Instances();
		insts.addInstance(new Instance("1,2.21,aa1, ,ee",","));
		insts.addInstance(new Instance("2,2.31,aa, ,ee",","));
		insts.addInstance(new Instance("3,7.21,aa,7,qe",","));
		insts.addInstance(new Instance("4,5.2,aaaa, ,uuu",","));
		
		insts.checkBoundry();
		
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		
		indexs.add(2);
		indexs.add(1);
		
		
		insts.removeColumn(indexs);
//		insts.removeColumn(0);
//		insts.removeColumn(1);
		
		System.out.println(insts);

	}
	
}
