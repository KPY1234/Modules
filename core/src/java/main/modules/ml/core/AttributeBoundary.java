package modules.ml.core;

public class AttributeBoundary {

	private double max = Double.MIN_VALUE;
	private double min = Double.MAX_VALUE;
	
	
	public AttributeBoundary(){
	}
	
	public void checkBoundary(double num){
		if(num > max)
			setMax(num);
		if(num < min)
			setMin(num);
	}
	
	public void setMax(double max){
		this.max = max;
	}
	
	public void setMin(double min){
		this.min = min;
	}
	
	public double getMax(){
		return max;
	}
	
	public double getMin(){
		return min;
	}
	
	public String toString(){
		return "max = "+max+", min = "+min;
	}
	
}
