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
        String sticky = run.stickyKey(convertedString);
        System.out.println("New Values: " + sticky);
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

    /**
     *  This method takes a string, finds the first 'H' value, makes the string behind 
     *  that value the sticky value and replaces all H's with the sticky value.
     *
     *  @param: converted string
     *  @return: stickied string
     */

    public String stickyKey(String value)
    {
        boolean first = true;
        for(int i = 0; i < value.length(); i++)
        {
            if(first && value.charAt(i) == 'H')
            {
                String stickiedValue = value.substring(0,i);
                System.out.println("Stickied value = " + stickiedValue);
                first = false;
                value = value.replaceAll("H", stickiedValue);
            }
        }
        return value;
    }
}

