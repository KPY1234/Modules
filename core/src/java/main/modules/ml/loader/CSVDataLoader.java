package modules.ml.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import modules.ml.core.Instance;
import modules.ml.core.Instances;


public class CSVDataLoader extends DataLoader {
	
	public CSVDataLoader(){
	}
	
	public Instances load(String filepath, int columns) throws IOException{
		
		File f = new File(filepath);
		String dataName = f.getName().split("\\.")[0];
		
		Instances insts = new Instances();
		insts.setDataName(dataName);
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		String line = br.readLine();	//Ū���Ĥ@��A�]�N�O������W��
		String[] columnNames = line.split(",");
		insts.getAtts().setAttName(columnNames);
		
		line = br.readLine();
		while(line!=null){
			insts.addInstance(new Instance(line, ","));
			line = br.readLine();
		}
		br.close();
		insts.checkBoundry();
		return insts;
	}
	
	// index start from 0
	public Instances load(String filepath, int columns, int labelIndex) throws IOException{
		
		File f = new File(filepath);
		String dataName = f.getName().split("\\.")[0];
		
		Instances insts = new Instances(labelIndex);
		insts.setDataName(dataName);
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		String line = br.readLine();
		String[] columnNames = line.split(",");
		insts.getAtts().setAttName(columnNames);
		line = br.readLine();
		while(line!=null){
		
			insts.addInstance(new Instance(line, ","));
			line = br.readLine();
		}
		br.close();
		insts.checkBoundry();
		return insts;
	}
	
	
	public static void main(String[] args) {
		
		CSVDataLoader csvdl = new CSVDataLoader();
		try {
			Instances insts = csvdl.load("./TestData/ml/CSVLoaderTest.csv",14,13);
			System.out.println(insts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
