package modules.ml.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import modules.ml.core.Attributes;
import modules.ml.core.AttributesNotSetException;
import modules.ml.core.Instance;
import modules.ml.core.Instances;


public class CSVDataLoader extends DataLoader {
	
	public CSVDataLoader(){
	}
	
	// index start from 0
	public Instances load(String filepath, Attributes att) throws IOException{
		
		File f = new File(filepath);
		String dataName = f.getName().split("\\.")[0];
		
		Instances insts = new Instances(att);
		insts.setDataName(dataName);
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		String line = br.readLine();
		String[] columnNames = line.split(",");
		insts.getAtts().setAttName(columnNames);
		line = br.readLine();
		while(line!=null){
		
			try {
				insts.addInstance(new Instance(line, ","));
			} catch (AttributesNotSetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			line = br.readLine();
		}
		br.close();
//		insts.checkAttributesBoundry();
		return insts;
	}
	
	
	public static void main(String[] args) {
		
		CSVDataLoader csvdl = new CSVDataLoader();
		String filePath = "./TestData/ml/CSVLoaderTest.csv";
		Attributes att = new Attributes(new String[]{"name", "sex", "phone", "height", "weight", "success"});
		try {
			Instances insts = csvdl.load(filePath, att);
			System.out.println(insts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
