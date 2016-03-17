package com.knightsofsomethingnotable;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    public static ArrayList<String> getCommand() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toUpperCase();
        String command = "";
        String target = "";
        ArrayList<String> parsedInput = new ArrayList<>();
        
        if(input.contains(" ")){
        	command = input.substring(0, input.indexOf(" "));
        	target = input.substring(input.indexOf(" ")+1).toLowerCase();
    	} else {
    		command = input;
    		target = "";
    	}
        
        parsedInput.add(command);
        parsedInput.add(target);
        
        //todo
        //2 element array; split input string on first space if exists
        //1st element is the enum command; 2nd element is the object to act on
        
        return parsedInput;
    }

}
