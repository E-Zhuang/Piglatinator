
/**
 * Write a description of class Piglatinator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Piglatinator
{
    private String input, nextTag, output;
    private int length, nextTagIndex, stringIndex;
    
    /**
     * Constructor for objects of class Piglatinator that holds information about the input String, the length 
     * of the input, the next tag (word or punctuation), the current output, and where the next tag begins. 
     */
    public Piglatinator(String in)
    {
        input = in;                         //English text that is meant to be translated
        length = input.length();            //length of text
        nextTagIndex = 0;                   //index of where the next tag begins
        nextTag = "";                       //nextTag is where the current tag will be stored
        output = "";                        //output is the running String that stores all translated words
        stringIndex = 0;                    // stringIndex is the index of the first vowel
                                            // in the nextTag String minus one
    }
    
    /**
     * This modifier method translates English into Pig Latin. First each individual tag is taken from
     * the input string and then findVowels() and findPunct() are called to find the vowels in the tag
     * and if that tag is a punctuation mark or not. Once those values are returned, the program is 
     * then able to modify the tag based on the conditions for Pig Latin and add it to the ongoing 
     * output string. 
     */
        public String piglatinator()
    {
        int ifVowel, ifPunct;
        String modNextTag, start, end;
        
        nextTag = getNextTag();                 //retrieves the next tag in the input String
        start = "";
        end = "";
        
        while (!nextTag.equals(""))
        {
            ifVowel = findVowels();             //where is the first vowel
            ifPunct = findPunct();              //is the tag punctuation
            
            if (ifPunct == 1 && nextTag.length() == 1) //if the tag is punctuation, just add directly to output w/out modifications
            {
                output += nextTag;              
            }
            else 
            {
                if ((ifVowel == -1)) //if there is no vowels, add "ay" and then add to output
                {
                    modNextTag = nextTag + "ay";
                    output += modNextTag;
                }
                else
                {
                    if (ifVowel == 1) //if the first letter is a vowel, add "yay" and then add to output
                    {
                        modNextTag = nextTag + "yay";
                        output += modNextTag;
                    }
                    else
                    {
                        if (ifVowel == 2)  //the second letter is a vowel, move first letter to the end, add "ay", then add
                                           //to the output String
                        {
                            start += nextTag.charAt(0);
                            end = nextTag.substring(1);
                            if (start.charAt(0) >= 'A' && start.charAt(0) <= 'Z') //tests to see if tag begins with a capital letter
                                end = capFirst(end);
                            modNextTag = end + start.toLowerCase() + "ay";
                            output += modNextTag;
                        }
                        else  //the first two letters are not vowels, move characters up until the first vowel to the end,
                              //add "ay", then add to the output String
                        {
                            start = nextTag.substring(0,ifVowel - 1);
                            end = nextTag.substring(ifVowel - 1);
                            if (start.charAt(0) >= 'A' && start.charAt(0) <= 'Z') //tests to see if tag begins with a capital letter
                                end = capFirst(end);
                            modNextTag = end + start.toLowerCase() + "ay";
                            output += modNextTag;
                        }
                    }
                }
            }
            
            nextTag = getNextTag();             //refreshes nextTag
            start = "";                         //clears start String
            end = "";                           //clears end String
        }
        
        return output;                          //returns the whole translated line of text
        
    }
    
    /**
     * This method gets the next word, punctuation, or space in the input string by using a nested
     * if else structure and a while loop to add all the characters to nextTag as long as they are 
     * in the alphabet. If not, the loop stores it as a single character.
     */
    private String getNextTag()
    {
        String nextTag = "";
        char letter;
        int offset;
        
        offset = 0;
        
        if (nextTagIndex < input.length())
        {
            letter = input.charAt(nextTagIndex + offset);
            if ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))
            {
                while ((letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z'))
                {
                    nextTag += letter;
                    offset++;
                    
                    //checks if the character is in the string  
                    if((nextTagIndex + offset) < input.length())
                    {
                        letter = input.charAt(nextTagIndex + offset);
                    }
                    //if the character is not part of the string (at the end), then letter = 0
                    //which breaks the loop
                    else
                        letter = 0;
                        
                }

                nextTagIndex += offset;
            }
            else
            {
                nextTag += letter;
                nextTagIndex++;
            }
        }
        
        return nextTag;
    }
    
    /**
     * This method is used to find the vowels in a tag and then it either returns the index of the vowel
     * or it returns -1 if there are no vowels. This is done by a nested while do-while structure that 
     * moves through the string of vowels "aeiouAEIOU" and compares it to each letter in the tag. 
     */
    private int findVowels()
    {
        String vowels, testString, testVowel;
        int vowelIndex, output;
        
        vowels = "aeiouAEIOU";            // all vowels that are tested      
        output = -1;                      // output shows where in the String the first vowel is, if any
        stringIndex = 0;
        
        if (findPunct() == 0)
        {
            do
            {
                vowelIndex = 0;                   // vowelIndex is the index for the string of vowels
                testString = "";                  // empty string so char can be concatenated to it
                testVowel = "";                   // empty string so char can be concatenated to it
                testString += nextTag.charAt(stringIndex);  //tests each letter in each tag
                stringIndex++;
                
                testVowel += vowels.charAt(vowelIndex);     //tests the next vowel in the string of vowels  
                while (vowelIndex < (vowels.length()) && !testString.equals(testVowel)) 
                {   
                    testVowel = "";
                    testVowel += vowels.charAt(vowelIndex);
                    
                    if(vowelIndex < vowels.length())
                        vowelIndex++;
                }
                
            }
            while (!testString.equals(testVowel) && stringIndex < nextTag.length());
        }
        
        if (stringIndex != nextTag.length() + 1)
            output = stringIndex;
            
        if (nextTag.charAt(nextTag.length() - 1) != 'a' && nextTag.charAt(nextTag.length() - 1) != 'e' &&
            nextTag.charAt(nextTag.length() - 1) != 'i' && nextTag.charAt(nextTag.length() - 1) != 'o' &&
            nextTag.charAt(nextTag.length() - 1) != 'u' && stringIndex == nextTag.length()) 
            output = -1;
            
        return output;
    }
    
    /**
     * This method tests to see if a tag is a punctuation mark or not through the use of an if-statement
     * that compares the tag to characters of the alphabet. 
     */
    private int findPunct()
    {
        int ifPunct;
        ifPunct = 0;            //ifPunct == 0 if there is no punctuation
        
        if (!(('a' <= nextTag.charAt(0)) && (nextTag.charAt(0) <= 'z')) && 
            !(('A' <= nextTag.charAt(0)) && (nextTag.charAt(0) <= 'Z')))
        {
            ifPunct = 1;        //ifPunct == 1 if there is punctuation
        }
        
        return ifPunct;
    }
    
    /**
     * This method capitalizes the first letter of the input by using substrings to seperate the first 
     * letter from the rest of the string, make it an upper case letter through toUpperCase(), and 
     * reconstructs the input so that the return of the method is the same as the input but the first 
     * letter is capitalized. 
     */
    private String capFirst(String input)
    {
        String start, end;
        start = "";
        
        start += input.charAt(0);
        end = input.substring(1);
        
        start = start.toUpperCase();
        
        return start + end;
    }
    
}
