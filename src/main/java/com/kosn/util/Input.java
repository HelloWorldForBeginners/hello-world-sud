package com.kosn.util;
import java.util.Scanner;

public class Input {
	Input() {}
	
	static String target = "";
    
	public static String getCommand() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toLowerCase();
        String command = "";
        
        if(input.contains(" ")){
        	command = input.substring(0, input.indexOf(" "));
        	target = input.substring(input.indexOf(" ")+1).toLowerCase();
    	} else {
    		command = input;
    		target = "";
    	}
        
        return command;
    }
}
