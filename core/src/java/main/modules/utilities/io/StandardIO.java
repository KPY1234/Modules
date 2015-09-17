package modules.utilities.io;

public class StandardIO {

	public static void printArray(Object[] objs, String delimit){
		
		for(int i=0;i<objs.length;i++){
			System.out.print(objs[i].toString());
			if(i!=objs.length-1)
				System.out.print(delimit);
		}

	}
	
	public static void printArray(int[] ints, String delimit){
		
		for(int i=0;i<ints.length;i++){
			System.out.print(ints[i]);
			if(i!=ints.length-1)
				System.out.print(delimit);
		}
	}
	
	public static void printArray(double[] doubles, String delimit){
		
		for(int i=0;i<doubles.length;i++){
			System.out.print(doubles[i]);
			if(i!=doubles.length-1)
				System.out.print(delimit);
		}
	}
	
	public static void main(String[] args) {
		
		String[] strs = new String[]{"AA", "BB","CC"};
		StandardIO.printArray(strs, ",");
		
		
		System.out.println();
		int[] ints = new int[]{1,2,3,4,};
		StandardIO.printArray(ints, ",");
		
		System.out.println();
		double[] doubles = new double[]{1.2,3.3,6.6,4.5};
		StandardIO.printArray(doubles, ",");
		
	}
}
