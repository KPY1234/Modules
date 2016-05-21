package modules.utilities;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayHandler {

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
	
	
	public static Object[] add(Object[] objs, Object obj){
		
		Object[] newObjs = Arrays.copyOf(objs, objs.length+1);
		newObjs[objs.length] = obj;
		objs = null;
	
		return newObjs;
	}
	
	public static int[] add(int[] ints, int integer){

		int[] newInts = Arrays.copyOf(ints, ints.length+1);
		newInts[ints.length] = integer;
		ints = null;
	
		return newInts;
	}
	
	public static double[] add(double[] doubles, double doub){

		double[] newDoubles = Arrays.copyOf(doubles, doubles.length+1);
		newDoubles[doubles.length] = doub;
		doubles = null;
	
		return newDoubles;
	}
	
	public static <T> T[] remove(T[] ts, int index){
		
		@SuppressWarnings("unchecked")
		T[] newArr = (T[]) Array.newInstance(ts.getClass().getComponentType(), ts.length-1);
		System.arraycopy(ts, 0, newArr, 0, index);
	    System.arraycopy(ts, index+1, newArr, index, ts.length-index-1);
	    
		return newArr;
	}
	
	public static <T> T[] concatenate(T[] a, T[] b) {
	    int aLen = a.length;
	    int bLen = b.length;

	    @SuppressWarnings("unchecked")
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);

	    return c;
	}
	
	public static int[] concatenate(int[] a, int[] b) {
	    int aLen = a.length;
	    int bLen = b.length;
	    
	    int[] c =  (int[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);

	    return c;
	}
	
	public static double[] concatenate(double[] a, double[] b) {
	    int aLen = a.length;
	    int bLen = b.length;
	    
	    double[] c =  (double[]) Array.newInstance(a.getClass().getComponentType(), aLen+bLen);
	    System.arraycopy(a, 0, c, 0, aLen);
	    System.arraycopy(b, 0, c, aLen, bLen);

	    return c;
	}
	
	public static void main(String[] args) {
		
		String[] strs = new String[]{"AA", "BB","CC"};
		ArrayHandler.printArray(strs, ",");
		
		System.out.println();
		strs = (String[]) ArrayHandler.add(strs, "DD");
		ArrayHandler.printArray(strs, ",");
		
		System.out.println();
		int[] ints = new int[]{1,2,3,4,};
		ArrayHandler.printArray(ints, ",");
		
		System.out.println();
		double[] doubles = new double[]{1.2,3.3,6.6,4.5};
		ArrayHandler.printArray(doubles, ",");
		
		System.out.println();
		String[] a = new String[]{"CC", "DD"};
		String[] b = new String[]{"WW", "ZZ"};
		String[] c = ArrayHandler.concatenate(a, b);
		ArrayHandler.printArray(c ,",");
		
		System.out.println();
		int[] inta = new int[]{1, 2};
		int[] intb = new int[]{3, 4};
		ArrayHandler.printArray(ArrayHandler.concatenate(inta, intb),",");
		
		System.out.println();
		String[] str2 = new String[]{"QQ", "NN", "RR", "YY"};
		String[] str3 = ArrayHandler.remove(str2, 0);
		ArrayHandler.printArray(str3, ",");
	}
}
