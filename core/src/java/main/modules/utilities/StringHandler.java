package modules.utilities;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {
	
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
			index = m.end();

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
			index = m.end();

		return index;
	}
	
}
