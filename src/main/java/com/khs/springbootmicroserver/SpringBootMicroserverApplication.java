package com.khs.springbootmicroserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// @SpringBootApplication
public class SpringBootMicroserverApplication {

	public static void main(String[] args) throws Exception {
		URLReader.main(args);
//		if(args.length==0){
//			System.out.println("Error: required arguments missing. Please list one or more runnable jar files as URLs."
//					+ "\r\nExample: \r\n"
//					+ "java -jar spring-boot-microserver.jar file:///tmp/SpringBootApp1.jar file:///tmp/SpringBootApp2.jar");
//			return;
//		}
//		ClassLoader[] classLoaders = new ClassLoader[args.length];
//		for(int i=0;i<classLoaders.length;i++) {
//			URL u = new URL("file://");
//			ClassLoader cl = new URLClassLoader(new URL[]{u});
//			classLoaders[i]=cl;
//			
//		}
//		
//		
//		List<ArrayList<URL>> resources = new ArrayList<>();
//		for(int i=0;i<classLoaders.length;i++) 
//		{	
//			List<URL> resourceUrls = Collections.list(classLoaders[i].getResources(""));
//			for(URL u : resourceUrls)
//			{
//				System.out.println("classloader "+i+" Resource: "+u);
//			}
//		}
	}
}
class URLReader {
    public static void main(String[] args) throws Exception {
    	int i=0;
        URL[] oracle = new URL[args.length];
        String[] rtn = new String[args.length];
        for(String arg : args) {oracle[i]=new URL(args[i]);rtn[i]="";
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle[i].openStream()));
        StringWriter sw = new StringWriter();
        
        String inputLine;
        int in1=1;
        while ((in1=in.read())!=-1) {
            System.out.write(in1);
            sw.write(in1);
        }
        rtn[i]=sw.toString();
        in.close();
        i++;
        }
    }
}
