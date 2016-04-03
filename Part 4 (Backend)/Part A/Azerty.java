import java.util.*;

/**
 * This program accepts a user input and converts the AZERTY pressed to QWERTY.
 *
 * @author Pranav Kumar
 * @version 3/26/16
 */
 

public class Azerty
{
    private HashMap<Character, Character> hm;

    /**
     *  This constructor initializes the hashmap and produces
     *  QWERTY values for AZERTY keys.
     *
     */

    Azerty()
    {
        hm = new HashMap<Character, Character>();
        hm.put('A', 'Q'); hm.put('a', 'q'); 
        hm.put('Z', 'W'); hm.put('z', 'w');
        hm.put('Q', 'A'); hm.put('q', 'a');
        hm.put('M', ':'); hm.put('w', 'z');
        hm.put('W', 'Z');
        hm.put('m', ':');
        hm.put(',', 'M');
    }

    public static void main(String [] args)
    {
        Azerty run = new Azerty();
        String input = run.userInput();
        char[] chars = input.toCharArray();
        String convertedString = run.convert(chars);
        System.out.println(convertedString);
    }

    /**
     *  This method takes in the users input
     *
     *  @return a string of letters
     */
    
    public String userInput()
    {
        System.out.println("Please enter a statement from your AZERTY keyboard that will be converted into its representive QWERTY keys:");
        Scanner link = new Scanner(System.in);
        String word = link.nextLine();
        return word;
    }

    /**
     *  This method changes the string based on the mapping of the QWERTY board
     *
     *  @param 
     *  @return a string of letters
     */

    public String convert(char[] letters)
    {
        String newValue = "";
        for(int i = 0; i < letters.length; i++)
        {
            if(hm.containsKey(letters[i]))
            {
                newValue += hm.get(letters[i]);
            }
            else
            {
                newValue += letters[i];
            }
        }
        return newValue;
    }
}

