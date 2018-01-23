package com.khs;


import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.script.ScriptEngineManager;

import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class SpringBootJsonRestClientApplication {

	public static void main(String[] urlAndMethodAndParameters) throws Exception 
	{
	
//		urlAndMethodAndParameters= new String[]{"https://www.google.com","GET","q=test"};
		
		String u3[] = new String[]{"http://www.keyholesoftware.com","GET",""};
		if(urlAndMethodAndParameters==null){ urlAndMethodAndParameters = new String[] {}; }
		if(urlAndMethodAndParameters.length>0 && urlAndMethodAndParameters[0]!=null) {u3[0]=urlAndMethodAndParameters[0];}
		if(urlAndMethodAndParameters.length>1 && urlAndMethodAndParameters[1]!=null) {u3[1]=urlAndMethodAndParameters[1];}
		if(urlAndMethodAndParameters.length>2 && urlAndMethodAndParameters[2]!=null) {u3[2]=urlAndMethodAndParameters[2];}
		if(urlAndMethodAndParameters.length==0)
		{
			System.out.println("Required Parameters: URL   METHOD   REQUESTSTRING (example: http://keyholesoftware.com   GET   paramname1=paramvalue1");
		}
		else {
			System.out.println(fetchURL(u3[0], u3[1], u3[2]).toString());
		}
	}
	public static String encodeURIComponent(String s) throws Exception{
		String rtn = "(function() {var s0='"+s.replaceAll("'", "\\'")+"'; return ( encodeURIComponent( s0 ) ); })()";
		rtn+=new ScriptEngineManager().getEngineByName("JavaScript").eval(rtn);
		return rtn;
	}
	public static String decodeURIComponent(String s) throws Exception{
		String rtn = "(function() {var s0='"+s.replaceAll("'", "\\'")+"'; return ( decodeURIComponent( s0 ) ); })()";
		rtn+=new ScriptEngineManager().getEngineByName("JavaScript").eval(rtn);
		return rtn;
	}
	
	public static JSONObject fetchURL(String url, String method, JSONObject parameters) throws Exception
	{
		// TODO: complete this logic to return "p1=v1&p2=v2"...
		String rtn = "{}";
		
		return new JSONObject(rtn);
	}
	public static JSONObject fetchURL(String url, String method, String parameterString) throws Exception
	{
		if(parameterString!=null && parameterString.length()!=0 && "GET".equalsIgnoreCase(method))
		{
			url += "?"+parameterString;
			parameterString = "";
		}
		URL u = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) u.openConnection();
		connection.setRequestMethod(method);
		connection.setDefaultUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setDoOutput(true);
		if(parameterString!=null && parameterString.trim().length()==0) { connection.setDoOutput(false); }
		else{ 
			connection.setDoOutput(true);
			PrintWriter osw=new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
			osw.print(parameterString);
			osw.write(-1);
			osw.close();
		}
		if(parameterString==null) {parameterString="";}
		parameterString=parameterString.trim();
		InputStreamReader ins = new InputStreamReader( new BufferedInputStream( connection.getInputStream() ) );
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		int i;
		while((i=ins.read())!=-1){ bw.write(i); }
		bw.flush();
		JSONObject rtn = new JSONObject();
		String s = String.valueOf(sw.toString()).trim();
		if(s.startsWith("[")) { rtn = new JSONObject("{\"arrayValue\":"+s+"}"); }
		else if(s.length()>0) {rtn.put("responseBody", s);}
		return rtn;
	}
	public static String join(String[] a,String jstr) 
	{
		String rtn="";   
		boolean isFirst = true;
		for(String ae : a)
		{
			if(!isFirst) {rtn+=jstr;}
			else {isFirst=false;}
			rtn+=ae;
		}
		return rtn;
	}
}
