package modules.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import modules.struct.Tuple;
import modules.utilities.StringHandler;

public class CSVReader {
	
	private ArrayList<Tuple> contentTuples;
	
	public CSVReader(){
		contentTuples = new ArrayList<Tuple>();
		
	}
	
	public void read(String filePath, int startRow, int endRow) throws IOException{
		clear();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		int count=1;
		while(line!=null){
			if(count>=startRow && count<=endRow){
				Tuple tuple = getTupleFromLine(line);
				contentTuples.add(tuple);
				
			}
		
			line = br.readLine();
			count++;
		}
	}
	
	public void read(String filePath, int startRow) throws IOException{
		clear();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		int count=1;
		while(line!=null){
			if(count>=startRow){
				Tuple tuple = getTupleFromLine(line);
				contentTuples.add(tuple);
				
			}
	
			line = br.readLine();
			count++;
		}
	}
	
	private Tuple getTupleFromLine(String line){
		
		Tuple tuple = new Tuple();
		
		if(line.contains("\"")){
		
			StringTokenizer st = new StringTokenizer(line, ",");
			String cellStr = "";
			while(st.hasMoreTokens()){
				String splitStr = st.nextToken();
				cellStr += splitStr;
				if(StringHandler.appearCount(cellStr, "\"")%2==1){
					cellStr += ",";
				}
				else{
					tuple.addCell(cellStr.trim());
//					System.out.println(cellStr.trim());
					cellStr = "";
				}
			}
		}else{
			
			String[] lineSplit = line.split(",");
			for(int i=0;i<lineSplit.length;i++)
				tuple.addCell(lineSplit[i].trim());
			
		}
		return tuple;
	}
	
	private void clear(){
		contentTuples.clear();
	}
	
	public ArrayList<Tuple> getContentTuples(){
		return contentTuples;
	}
	
	public ArrayList<Tuple> getDataFromColumns(int... columnIndexs){
		
		Arrays.sort(columnIndexs);
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		
		for(int i=0;i<contentTuples.size();i++){
			
			Tuple temTuple = new Tuple();
			
			for(int j=0;j<columnIndexs.length;j++){
				int index = columnIndexs[j];
				try{
					temTuple.addCell(contentTuples.get(i).getCell(index).getValue());
				}catch(IndexOutOfBoundsException ioobe){
					System.err.println("Your column index: "+index+" out of bound, column size: "+contentTuples.get(i).size());
				}
			}
			tuples.add(temTuple);
		}
		return tuples;
	}
	
	public ArrayList<Tuple> getDataFromColumns(ArrayList<Integer> columnIndexs){
		
		columnIndexs.sort(null);
		
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		
		for(int i=0;i<contentTuples.size();i++){
			
			Tuple temTuple = new Tuple();
			
			for(int j=0;j<columnIndexs.size();j++){
				int index = columnIndexs.get(j);
				try{
					temTuple.addCell(contentTuples.get(i).getCell(index).getValue());
				}catch(IndexOutOfBoundsException ioobe){
					System.err.println("Your column index: "+index+" out of bound, column size: "+contentTuples.get(i).size());
				}
			}
			tuples.add(temTuple);
		}
		return tuples;
	}
	
	public ArrayList<Tuple> getDataFromColumnsLimited(int[] columnIndexs, int limitSize){
		
		Arrays.sort(columnIndexs);
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		
		for(int i=0;i<contentTuples.size();i++){
			if(contentTuples.get(i).size()<limitSize)
				continue;
			
			Tuple temTuple = new Tuple();
			for(int j=0;j<columnIndexs.length;j++){
				int index = columnIndexs[j];
				try{
					temTuple.addCell(contentTuples.get(i).getCell(index).getValue());
				}catch(IndexOutOfBoundsException ioobe){
					System.err.println("Your column index: "+index+" out of bound, column size: "+contentTuples.get(i).size());
				}
			}
			tuples.add(temTuple);
		}
		return tuples;
	}
	
	public static void main(String[] args) {
		
		CSVReader csvr = new CSVReader();
		
		try {
			csvr.read("./TestData/CSVReaderTest.csv", 1);
//			for(int i=0;i<csvr.getContentTuples().size();i++)
//				System.out.println(csvr.getContentTuples().get(i));
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(1);
			list.add(3);
			list.add(10);
			list.add(7);
			System.out.println(csvr.getDataFromColumns(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
