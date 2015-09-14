package modules.utilities;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {
	
	public static String delete(String str, int fromIndex, int endIndex){
		String firstPart = str.substring(0, fromIndex);
		String secondPart = str.substring(endIndex+1);
		return firstPart+secondPart;
	}
	
	public static String[] splitByTokens(String str, String delimits){
		ArrayList<String> list = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(str,delimits);
		
		while(st.hasMoreElements())
			list.add(st.nextToken());
		
		String[] rtns = new String[list.size()];
		
		for(int i=0;i<rtns.length;i++)
			rtns[i] = list.get(i);
		
		return rtns;
	}
		
	public static int findHead(String str, String regex){
		int index = -1;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if(m.find())
			index = m.start();
			
		return index;
	}
		
	public static int findEnd(String str, String regex){
		int index = -1;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if(m.find())
			index = m.end()-1;

		return index;
	}
		
	public static int findHead(String str, String regex, int fromIndex){
		int index = -1;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		m = m.region(fromIndex, str.length());
		if(m.find())
			index = m.start();
			
		return index;
	}
		
	public static int findEnd(String str, String regex, int fromIndex){
		int index = -1;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		m = m.region(fromIndex, str.length());
		if(m.find())
			index = m.end()-1;

		return index;
	}
	
	public static int appearCount(String str, String matchStr){
		
		int index = str.indexOf(matchStr);
		int count = 0;
		while(index!=-1){
			count++;
			index = str.indexOf(matchStr, index+1);
		}
		return count;
	}
	
	public static void main(String[] args) {
		
//		System.out.println(StringHandler.delete("ABCDefgHIJK", 4, 6));
//		System.out.println(StringHandler.findEnd("<abc>  <w>\nAW<Snn>ns","<[^>]*>"));
		System.out.println(StringHandler.findHead("EAh1configedeBG","A.*BG&&.*(?<!config)$"));
		
//		System.out.println(appearCount("AABB,CCSS,GGHH,AA","CC"));
		
	}
}
