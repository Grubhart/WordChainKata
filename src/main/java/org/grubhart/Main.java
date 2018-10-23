package org.grubhart;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter first word: ");

        // get their input as a String
        String firstWord = scanner.next();

        // prompt for their age
        System.out.print("Enter second word: ");

        // get the age as an int
        String secondWord = scanner.next();

        WordChain wordChain = new WordChain();
        wordChain.loadDictionaryAsLowerCase();
        String result = wordChain.getChain(firstWord,secondWord,null);
        System.out.println(result);

    }
}
