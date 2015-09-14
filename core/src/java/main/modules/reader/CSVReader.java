package modules.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	
	
	public static void main(String[] args) {
		
		CSVReader csvr = new CSVReader();
		
		try {
			csvr.read("./TestData/CSVReaderTest.csv", 1);
			for(int i=0;i<csvr.getContentTuples().size();i++)
				System.out.println(csvr.getContentTuples().get(i));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
