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
	
	public Instances(){
		atts = new Attributes();
		insts = new ArrayList<Instance>();
	}
	
	public Instances(Attributes atts){
		this.atts = atts;
		insts = new ArrayList<Instance>();
	}
	
	
	public void addInstance(Instance inst) throws AttributesNotSetException{
	
		if(!hasSetAttributes()){
			String message = "例外發生: 未設定屬性(Attributes) ";
			throw new AttributesNotSetException(message);
		}
		
		insts.add(inst);
		int instNum = insts.size()-1;
		atts.checkTypes(instNum, inst);
		checkAttributesBoundry(inst);
		
		if(isLabeled()){
			String label = inst.get(atts.getLabelIndex());
			ArrayList<String> labels = atts.getNominals().get(atts.getLabelIndex());
			if(labels.contains(label))
				labels.add(label);
		}
	}
	
	
	private boolean hasSetAttributes(){
		if(atts.size()==0)
			return false;
		else
			return true;
	}
	
	private boolean isLabeled(){
		if(atts.getLabelIndex() >= 0)
			return true;
		else
			return false;
	}
	
	private void checkAttributesBoundry(Instance inst){
		
		ArrayList<String> attTypes = atts.getTypes();
		Vector<String> vec = inst.getRecords();
		for(int j=0;j<vec.size();j++){
			String value = vec.elementAt(j);
			if(isStringEqualNull(value))
				continue;
			if(attTypes.get(j).equals("numeric")){
				double number = Double.parseDouble(value);
				
				if(!maxNums.containsKey(j))
					maxNums.put(j, number);
				else{
					double max = maxNums.get(j);
					if(number>max)
						maxNums.put(j, number);
				}
				
				if(!minNums.containsKey(j))
					minNums.put(j, number);
				else{
					double min = minNums.get(j);
					if(number<min)
						minNums.put(j, number);
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
	
	
//	public void checkAttributesBoundry(){
//		
//		ArrayList<String> attTypes = atts.getTypes();
//		
//		for(int i=0;i<insts.size();i++){
//			Instance inst = insts.get(i);
//			Vector<String> vec = inst.getRecords();
//			
//			for(int j=0;j<vec.size();j++){
//				
//				String value = vec.elementAt(j);
//				if(isStringEqualNull(value.trim()))
//					continue;
//				if(attTypes.get(j).equals("numeric")){
//					double numeric = Double.parseDouble(value);
//					
//					
//					if(!maxNums.containsKey(j))
//						maxNums.put(j, numeric);
//					else{
//						double max = maxNums.get(j);
//						if(numeric>max)
//							maxNums.put(j, numeric);
//					}
//					
//					
//					if(!minNums.containsKey(j))
//						minNums.put(j, numeric);
//					else{
//						double min = minNums.get(j);
//						if(numeric<min)
//							minNums.put(j, numeric);
//					}
//					
//				}
//				else{
//					if(!nominals.containsKey(j)){
//						ArrayList<String> nominalValues = new ArrayList<String>();
//						nominalValues.add(value);
//						nominals.put(j, nominalValues);
//					}
//					else{
//						ArrayList<String> nominalValues = nominals.get(j);
//						if(!nominalValues.contains(value))
//							nominalValues.add(value);
//					}
//				}
//			}
//		}
//	}
	
	private boolean isStringEqualNull(String str){
		
		if(str.equals("NULL")||str.equals("null"))
			return true;
		else
			return false;
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
	
//	public void removeColumn(int columnIndex){
//		
//		atts.remove(columnIndex);
//		
//		if(columnIndex == atts.getLabelIndex())
//			atts.setLabelIndex(-1);
//		if(atts.getLabelIndex() > columnIndex)
//			atts.setLabelIndex(atts.getLabelIndex()-1);
//			
//		if(maxNums.containsKey(columnIndex))
//			maxNums.remove(columnIndex);
//		if(minNums.containsKey(columnIndex))
//			minNums.remove(columnIndex);
//		if(nominals.containsKey(columnIndex))
//			nominals.remove(columnIndex);
//		
//		Object[] keys = maxNums.keySet().toArray();
//		for(int i=0;i<keys.length;i++){
//			if((int)keys[i] > columnIndex){
//				Double maxNum = maxNums.get(keys[i]);
//				maxNums.remove(keys[i]);
//				maxNums.put(((int)keys[i])-1, maxNum);
//			}
//		}
//		
//		keys = minNums.keySet().toArray();
//		for(int i=0;i<keys.length;i++){
//			if((int)keys[i] > columnIndex){
//				Double minNum = minNums.get(keys[i]);
//				minNums.remove(keys[i]);
//				minNums.put(((int)keys[i])-1, minNum);
//			}
//		}
//		
//		keys = nominals.keySet().toArray();
//		for(int i=0;i<keys.length;i++){
//			if((int)keys[i] > columnIndex){
//				ArrayList<String> nominalList = (ArrayList<String>) Cloner.clone(nominals.get(keys[i]));
//				nominals.remove(keys[i]);
//				nominals.put(((int)keys[i])-1, nominalList);
//			}
//		}
//		
//		for(int i=0;i<insts.size();i++){
//			insts.get(i).removeColumn(columnIndex);
//		}
//	}
	
	public void clearData(){
		insts.clear();
	}
	
//	public void removeColumn(ArrayList<Integer> columnIndexs){
//		
//		Object[] indexs = columnIndexs.toArray();
//		Arrays.sort(indexs);
//		
//		int count=0;
//		for(int i=0;i<indexs.length;i++){
//			int index = (int)indexs[i] - count;
//			removeColumn(index);
//			count++;
//		}
//	}
	
	public Attributes getAtts(){
		return atts;
	}
	
//	public ArrayList<String> getLabels(){
//		return labels;
//	}
//	
//	public HashMap<Integer, Double> getMaxNums(){
//		return maxNums;
//	}
//	public HashMap<Integer, Double> getMinNums(){
//		return minNums;
//	}
//	public HashMap<Integer, ArrayList<String>> getNominals(){
//		return nominals;
//	}
	
	public int getLabelIndex(){
		return atts.getLabelIndex();
	}
	
	public Instance get(int index){
		return insts.get(index);
	}
	
	public int size(){
		return insts.size();
	}
	
//	public String toString(){
//		
//		String str = "max boundry: "+getMaxNums().toString();
//		str += "\nmin boundry: "+getMinNums().toString();
//		str += "\nnominals boundry: "+getNominals().toString();
//		
//		if(atts.getLabelIndex()>-1)
//			str += "\nlabels = "+getNominals().get(atts.getLabelIndex()).toString();
//		
//		str += "\n@data";
//		str += "\n";
//		
//		for(int i=0;i<insts.size();i++){
//			str += insts.get(i).toString()+"\n";
//		}
//		return str;
//	}
	
	public static void main(String[] args) {
		
		String[] columns = new String[]{"f1", "f2", "f3", "f4", "Label"};
		
		Instances insts = new Instances();
		Attributes atts = new Attributes(columns, 4);
		insts.setAttributes(atts);
		try {
			insts.addInstance(new Instance("1,2.21,aa1, ,ee",","));
			insts.addInstance(new Instance("2,2.31,aa, ,ee",","));
			insts.addInstance(new Instance("3,7.21,aa,7,qe",","));
			insts.addInstance(new Instance("4,5.2,aaaa, ,uuu",","));
			
		} catch (AttributesNotSetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		insts.checkAttributesBoundry();
		
		System.out.println(insts);
		
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		
		indexs.add(2);
		indexs.add(1);
		
		
//		insts.removeColumn(indexs);
//		insts.removeColumn(0);
//		insts.removeColumn(1);
		
		System.out.println(insts);

	}
	
}
