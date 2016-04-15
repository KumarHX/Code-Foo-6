import java.io.File;
import java.util.Scanner;

/**
 * Based on a user-input, find all words that can be created and the highest scoring word (Scrabble Rules)
 *
 * @author Pranav Kumar
 * @version 3/26/16
 */
 
public class ScrabbleWord
{
    /**
     *  This method runs the program and prints to the console all the words found and the most valuable word found.
     */
    public static void main (String [] args)
    {
        System.out.println("\nWelcome to Scrabble Word! This program finds the most valuable word based on a scrabble hand. The rules the program works off are the point values for letters provided by http://scrabble.hasbro.com/en-us/faq and the double score multiplier for consecutive double letters.\n");
        System.out.print("Please enter a list of letters, from 3 to 12 letters long, without spaces -> ");    
        String input = userInput();    
        if(input == null)
        {
            System.exit(0);
        }
        String [] word = findWords(input);
        printWords(word);    
        //pointvalues of letters (alphabetical order)
        int [] table = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        String best = bestWord(word,table);
        if(best == null || best.equals(""))
        {
            System.out.println("No word from the IGN data source can be created with that input.");
            System.exit(1);
        }
        System.out.println("\n\nHighest scoring word: " + best + "\n");
    }    
        
    /**
     *  This method takes in a string of 3 - 12 letters and checks if the characters are from 'a' - 'z'
     *
     *  @return a string of letters
     */
    public static String userInput()
    {
        Scanner link = new Scanner(System.in);
        String word = link.next();
        char[] chars = word.toCharArray();
        boolean checkWord = false;                
        for(int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if((c < 97 || c > 122))
            {
                checkWord = true;
            }
        }
        link.close();
        if(!checkWord)
        {
            return word;
        } 
        else 
            System.out.println("Not a valid input");
            return null;
    }
        
    /**
     *  Using the user input, find all possible words that can be created
     *
     *  @param The user-input (a String)
     *  @return An array of strings with all words thst can be made with the input.
     */
    public static String [] findWords (String letters) 
    {                
        String total = "";
        try
        {
            Scanner scanner = new Scanner(new File("wordlist.txt"));
            while(scanner.hasNext())
            {
                String line = scanner.nextLine();
                if(matchletters(line, letters))
                    total = total.concat(line).concat(" ");
                }
                //System.out.println(total);
                return total.split(" ");
        }
        catch(Exception e)
        {
            System.out.println("Error found: " + e);
            return null;
        }
    }
        
    /**
     *  Determines if a word can be formed by a list of letters.
     *
     *  @param temp  The word to be tested.
     *  @param letters  A string of the list of letter
     *  @return   True if word can be formed, false otherwise
     */
    public static boolean matchletters (String temp, String letters)
    {
        String newLetters = letters;
        for(int i = 0; i < temp.length(); i++)
        {
            if(newLetters.indexOf(temp.charAt(i)) != -1)
            {
                newLetters = newLetters.replaceFirst(Character.toString(temp.charAt(i)), "");
            }
            else return false;
        }
        return true;
    }
        
    /**
     *  Print all the words in the array and space them out.
     *
     *  @param word  The string array containing the words.
     */
    public static void printWords (String [] word)
    {
        int lineCounter = 0;
        for(String each : word)
        {
            if(lineCounter % 5 == 0) 
            {
                System.out.print("\n");
            }

            if(!each.equals(""))
            {
                System.out.printf("%12.12s", each);
                lineCounter++;
            }                    
        }
    }
        
    /**
     *  Finds the highest scoring word according to Scrabble rules.
     *
     *  @param word  An array of words to check.
     *  @param scoretable  An array of 26 integer scores in letter order.
     *  @return   The word with the highest score.
     */
    public static String bestWord (String [] word, int [] scoretable)
    {            
        int score = 0;
        int maxScore = 0;
        String maxScoreString = "";
        for(String each : word)
        {                        
            score = getScore(each, scoretable);            
            if(score > maxScore)
            {
                maxScore = score;
                maxScoreString = each;
            }
        }                
        return maxScoreString;
    }
        
    /**
     *  This method calculates the value of a word according to Scrabble 
     *
     *  @param word:  The word to score
     *  @param scoretable:  The value of each letter sctored in an array
     *  @return an integer value of the word
     */
    public static int getScore (String word, int [] scoretable)
    {
        int wordScore = 0;
        char [] letters = word.toCharArray();
        String[] wordValues = word.split(" ");
        for(int i = 0; i < letters.length; i++ )
        {
            int score = letters[i];
            wordScore = wordScore + scoretable[score - 97];                
        }
        //Gives word double score if consecutive double letters 
        for (int j = 0; j < wordValues.length; j++)
        {
            for (int n = 0; n < (wordValues[j].length()-1); n++)
            {
                if(wordValues[j].charAt(n) == wordValues[j].charAt(n+1))
                {
                    wordScore = wordScore * 2;
                }
            }
        }
        return wordScore;
    }
}      






