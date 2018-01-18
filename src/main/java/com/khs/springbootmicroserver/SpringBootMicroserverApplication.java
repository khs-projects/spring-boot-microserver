package com.khs.springbootmicroserver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringBootMicroserverApplication {


	public static final Map<String, Object> env = Collections.synchronizedMap(new LinkedHashMap<String,Object>(){});
	
	
	
	public SpringBootMicroserverApplication(){
		
	
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootMicroserverApplication.class,args);
		//new SpringBootMicroserverApplication();
		//URLReader.main(args);
		// Class<?> regeneratedClass = this.defineClass(rawBytes, 0, rawBytes.length);

 
 
 
 

 
		
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

class FileUtil {
    public static String bytesToString(List<Integer> b) {StringWriter sw = new StringWriter();for(Integer i : b) {sw.write(0+i);}sw.flush();return sw.toString();}
    public static byte[] bytesToArray(List<Integer> b) {byte[] rtn = new byte[b.size()];int index=0;for(Integer i : b) {rtn[index++]=(byte)(0+i);}return rtn;}
	public static List<Integer> URLToBytes(String url) throws Exception {
    	List<Integer> rtn = new ArrayList<Integer>();
        BufferedReader in = new BufferedReader(
        new InputStreamReader(new URL(url).openStream()));
        int in1=1;
        while ((in1=in.read())!=-1) {rtn.add(new Integer(in1));}
        in.close();
        return rtn;
    } 	
    public static Map<String,List<Integer>> unzipFilesToBytes(InputStream r) throws Exception {
    	Map<String,List<Integer>> rtn = new LinkedHashMap<>();
        ZipInputStream zis = new ZipInputStream(r);//new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while(zipEntry != null){
            String fileName = zipEntry.getName()+"";
            List<Integer> fileData = new ArrayList<Integer>();
            int i;
            while ((i = zis.read())!=-1) {
            	fileData.add(new Integer(i));
            }
            rtn.put(fileName, fileData);
            zipEntry = zis.getNextEntry(); 
        }
        zis.closeEntry();
        zis.close();
        return rtn;
    }
}