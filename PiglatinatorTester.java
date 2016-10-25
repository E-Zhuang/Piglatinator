
/**
 * Lab 12.3 Piglatinator
 * Translates a line of English text into Pig Latin.
 * 
 * Emily Zhuang
 * Mr. Lantsberger
 * APCS:Period 4
 * 25 October 2016
 */

import chn.util.*;

public class PiglatinatorTester
{
    /**
     * This program converts English to Pig Latin until the user expresses that 
     * they no longer wish to translate anymore lines of text. This is done through 
     * the use of a while loop that ensures that the user does not say "n" or "N" that 
     * they do not want to continue. This class prompts the user for a line of text, 
     * returns the translated text, then asks if the user would like to translate another
     * line. 
     */
    public static void main(String[] args)
    {
        ConsoleIO keyboard = new ConsoleIO();       //ConsoleIO allows for input and output
        Piglatinator toPigLatin;                    //Piglatinator is the translator
        
        String input, output, cont;                 //Input is the string the user would like
                                                    //to translate. Output is the translated
                                                    //text. Cont is the response to whether 
                                                    //the user would like to continue or not.
        
        cont = "y";                                 //cont begins at "y" since it is assumed
                                                    //that if the user is running the program
                                                    //they have a line they would like to translate

        while (!cont.equals("n") && !cont.equals("N"))      //as long as they do not enter "n" or "N"
        {
            //prompts the user for a line of text and then instantiates Piglatinator, inputing the text
            System.out.println("I can translate English sentences and phrases into Pig Latin.");
            System.out.println("Please type an English sentence or phrase and then press <Enter>.");
            input = keyboard.readLine();
            toPigLatin = new Piglatinator(input);
            
            //stores the return value (translation) under output and prints it to the user
            output = toPigLatin.piglatinator();
            System.out.println("In Pig Latin that would be: ");
            System.out.println(output);
            System.out.println();
            
            //asks if the user would like to continue translating
            System.out.println("Would you like to translate another phrase? Enter y for" +
                               " yes or n for no. ");
            cont = keyboard.readLine();
            System.out.println();
        }
        

    }
}
