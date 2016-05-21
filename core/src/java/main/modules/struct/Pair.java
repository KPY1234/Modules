package modules.struct;

public class Pair<L,R>{

	private L l;
	private R r;
	
	public Pair(L l, R r){
		this.l = l;
		this.r = r;
	}
	
	public L getLeft(){
		return l;
	}
	
	public R getRight(){
		return r;
	}

	public String toString(){
		return "("+l.toString()+","+r.toString()+")";
	}
	
	public static void main(String[] args) {
		
		Pair<String, Integer> p = new Pair<String, Integer>("AA",5);
		System.out.println(p.getLeft()+"\t"+p.getRight());
		System.out.println(p.toString());
	}
	
}
