package com.meeno.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAnalyse {
    public static void main(String[] args) throws IOException{
    	String dirs = "c:\\";//"D:\\workspace3.4\\ops2\\WebContent\\WEB-INF\\jsp\\ops2";
    	File director = new File(dirs);
    	FileWriter requestContentFile = new FileWriter(dirs+"meeno_entity_memory.txt");

    	for(File file:director.listFiles()){
    		if(file.getName().startsWith("jvminfo20130603.")){
    			BufferedReader input = new BufferedReader(new FileReader(file));
    			String line = null;
    			while((line = input.readLine())!=null){
    				if(line.indexOf("com.meeno.")>0){
    					line = line.substring(line.indexOf(":")+1);
    					requestContentFile.write(line);
    				}

    			}
    			input.close();
    		}
    	}
    	requestContentFile.close();


    }

}
