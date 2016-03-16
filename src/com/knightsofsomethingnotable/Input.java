package com.knightsofsomethingnotable;
import java.util.Scanner;

public class Input {

    public static String getInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toUpperCase();
        
        //todo
        //2 element array; split input string on first space if exists
        //1st element is the enum command; 2nd element is the object to act on
        
        return input;
    }
}
