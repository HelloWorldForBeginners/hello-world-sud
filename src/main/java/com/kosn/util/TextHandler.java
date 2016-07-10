package com.kosn.util;

public class TextHandler {

    public static String tableifyCentered(String input) {
    	String returnValue = input;
    	String side = "append";
    	while (returnValue.length() < 10) {
    		if (side.equals("append")) {
        		returnValue += " ";
        		side = "prepend";
    		} else {
    			returnValue = " " + returnValue;
    			side = "append";
    		}
    	}
    	return returnValue;
    }

    public static String tableifyLeftAligned(String input) {
    	String returnValue = input;
    	while (returnValue.length() < 10) {
        		returnValue += " ";
    		}
    	return returnValue;
    }
    
    public static void printAsTwoColumnsLeftAligned(String colOne, String colTwo) {
    	System.out.format("%s%s\n", tableifyLeftAligned(colOne), tableifyLeftAligned(colTwo));
    }   
    public static void printAsTwoColumnsCentered(String colOne, String colTwo) {
    	System.out.format("%s%s\n", tableifyCentered(colOne), tableifyCentered(colTwo));
    }   


}
