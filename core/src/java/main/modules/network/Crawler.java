package modules.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import modules.utilities.string.StringHandler;

public class Crawler {

	
	public Crawler(){
		
		
		
	}
	
	
	public static String getSource(String urlStr) throws IOException{
		String sourceContent="";
		
		URL url = new URL(urlStr);
	    URLConnection urlc = url.openConnection();
	    BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    String inputLine;
	    while ((inputLine = in.readLine()) != null) 
	    	sourceContent+=inputLine+"\n";
	    in.close();
	     
	    return sourceContent;
	}
	
	public static String getPureContent(String urlStr) throws IOException{
		String sourceContent="";
		
		URL url = new URL(urlStr);
	    URLConnection urlc = url.openConnection();
	    BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    String inputLine;
	    while((inputLine = in.readLine()) != null) 
	    	sourceContent+=inputLine+"\n";
	    in.close();
	     
//	    System.out.println(sourceContent);
	    
	    int startIndex = 0;
	    int matchHeadIndex;
	    while((matchHeadIndex = StringHandler.findHead(sourceContent, "<[^<]*>", startIndex)) != -1){
	    	
	    	String partContent = sourceContent.substring(startIndex, matchHeadIndex).trim();
	    	
	    	if(!partContent.isEmpty())
	    		System.out.println(partContent);
	    	
	    	int matchEndIndex = StringHandler.findEnd(sourceContent, "<[^>]*>", startIndex);
	    	
//	    	System.out.println("match index: "+matchHeadIndex+"\t"+matchEndIndex);
//	    	System.out.println(sourceContent.substring(matchHeadIndex, matchEndIndex+1));
	    	startIndex = matchEndIndex+1;
	    }
	    
	    return sourceContent;
	}
	
	public static String getScriptContent(String urlStr) throws IOException{
		
		String sourceContent="";
		
		URL url = new URL(urlStr);
	    URLConnection urlc = url.openConnection();
	    BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
	    String inputLine;
	    while((inputLine = in.readLine()) != null) 
	    	sourceContent+=inputLine+"\n";
	    in.close();
	     
	    
	    String copiedSourceContent = sourceContent;
	    copiedSourceContent = copiedSourceContent.replaceAll("[\n\r]", "~");
	    
	    String scriptContent="";
	    
	    int startIndex = 0;
	    int matchHeadIndex;
	    while((matchHeadIndex = StringHandler.findHead(copiedSourceContent, "<script [^<]*</script>", startIndex)) != -1){
	    	
//	    	System.out.println("Content:"+sourceContent.substring(startIndex, matchHeadIndex).trim());
	    	int matchEndIndex = StringHandler.findEnd(copiedSourceContent, "<script [^<]*</script>", startIndex);
	    	
//	    	System.out.println("match index: "+matchHeadIndex+"\t"+matchEndIndex);
//	    	System.out.println(sourceContent.substring(matchHeadIndex, matchEndIndex+1));
	    	scriptContent+=sourceContent.substring(matchHeadIndex, matchEndIndex+1)+"\n";
	    	startIndex = matchEndIndex+1;
	    }
	    return scriptContent;
	}
	
	
	public static void main(String[] args) {
		
		String url = "https://tw.stock.yahoo.com/d/s/company_4417.html";
		try {
//			System.out.println(Crawler.getSource(url));
			Crawler.getPureContent(url);
//			System.out.println(Crawler.getScriptContent(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
