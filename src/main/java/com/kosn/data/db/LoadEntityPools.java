package com.kosn.data.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoadEntityPools {

	private LoadEntityPools () {}
	
	private static String path = "";

	public static void getPath() {

		File currDir = new File(".");
		path = currDir.getAbsolutePath();
		path = path.substring(0, path.length()-1);
	}
    
	public static Map<String, String> importCreatures() {

		getPath();
		
		File file = new File(path + "/src/main/resources/entityRepository/creatureMaster.txt");
		
		Scanner scan = null;
		
		Map<String, String> masterCreatureList = new HashMap<String, String>();
		
	    try {
	    	scan = new Scanner(file);
	
	    	while (scan.hasNextLine()) {
	    		String line = scan.nextLine();
	            String[] lineArray = line.split(",");
	            if (lineArray.length != 2) { 
	            	continue;
	            }
	            masterCreatureList.put(lineArray[0], lineArray[1]);
	            
	    	}
	    	
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	    	scan.close();
	    }
	    
	    return masterCreatureList;
	}
	
}
