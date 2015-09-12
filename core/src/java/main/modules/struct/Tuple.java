package modules.struct;

import java.util.ArrayList;


public class Tuple {
	ArrayList<Cell> Cells;
	
	public Tuple(){
		Cells = new ArrayList<Cell>();
	}
	
	public void addCell(String cell){
		Cells.add(new Cell(cell));
	}
	
	public Cell getCell(int index){
		return Cells.get(index);
	}
	
	public int size(){
		return Cells.size();
	}
	
	public String toString(){
		String str = "[ ";
		for(int i=0;i<Cells.size();i++){
			str += Cells.get(i);
			if(i!=Cells.size()-1)
				str += ",";
		}
		str += " ]";
		return str;
	}
}
