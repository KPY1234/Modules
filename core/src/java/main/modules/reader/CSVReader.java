package modules.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	
	private String content = "";
	
	public CSVReader(){
		
		
	}
	

	public void read(String filePath, int startRow, int endRow) throws IOException{
		clear();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		int count=1;
		while(line!=null){
			if(count>=startRow && count<=endRow)
				content += line+"\n";
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
			if(count>=startRow)
				content += line+"\n";
			line = br.readLine();
			count++;
		}
	}
	
	private void clear(){
		content = "";
	}
	
	public String getContent(){
		return content;
	}
	
	
	public static void main(String[] args) {
		
		CSVReader csvr = new CSVReader();
		
		try {
			csvr.read("./TestData/SQUOTE_EW_1040903.csv", 1);
			System.out.println(csvr.getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
