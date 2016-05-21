package modules.ml.core;

public class AttributesNotSetException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public AttributesNotSetException(String message){
		super(message);
	}
	
	public static void main(String[] args) throws AttributesNotSetException {
	
		int x=0;
	
		if(x==0){
			throw new AttributesNotSetException("x=0");
		}
		if(x==1){
			throw new AttributesNotSetException("x=1");
		}
	
	}
	
}
