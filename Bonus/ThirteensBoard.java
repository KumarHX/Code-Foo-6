import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends Board {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;

	private static int userPoints = 0;
	private static int CPUPoints = 0;


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ThirteensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Gets the user score
	 *
	 * @return user score
	 */

	 public int getUserScore()
	 {
	 	return userPoints;
	 }

	/**
	 * Sets the user score
	 *
	 * @param new user score
	 */

	 public void setUserScore(int a)
	 {
	 	userPoints = a;
	 }

	/**
	 * Gets the CPU score
	 *
	 * @return CPU score
	 */

	 public int getCPUScore()
	 {
	 	return CPUPoints;
	 }

	 /**
	 * Sets the CPU score
	 *
	 * @param new CPU score
	 */

	 public void setCPUScore(int a)
	 {
	 	CPUPoints = a;
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		if (selectedCards.size() == 1) {
			return containsKing(selectedCards);
		} 
		else if (selectedCards.size() == 2){
			return containsPairSum13(selectedCards);
		} 
		else {
			userPoints -= 1;
			return false;
		}
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	public boolean anotherPlayIsPossible() {
        List<Integer> cIndexes = cardIndexes();
		return containsPairSum13(cIndexes) || containsKing(cIndexes);
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum13(List<Integer> selectedCards) {
		if (selectedCards.size() < 2) {
            return false;
        }
        for (int i = 0; i < selectedCards.size() - 1; i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 13) {   
                	return true;
                }
            }
        }
        return false;
	}

	/**
	 * Look for a king in the selected cards.
	 * @param selectedCards the cards in the hand
	 * @return true if a king is
	 */
	private boolean containsKing(List<Integer> selectedCards) {
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("king")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This program allows the CPU to scan the hand and get points on pairs of 13's (every 12 seconds)
	 * @param allCards cards on the deck
	 * @param sum total value needed for sum
	 */
	public void printPairs(List<Integer> allCards, int sum) 
	{ 
		try 
		{
    		Thread.sleep(12000);
		} 
		catch(InterruptedException ex) 
		{
   			Thread.currentThread().interrupt();
		}
		for (int i = 0; i < allCards.size(); i++) 
		{
			for(int j = i + 1; j < allCards.size(); j++)
			{
				if(allCards.get(i) + allCards.get(j) == 13)
				{
					CPUPoints += 1;
				}
			}
		}
	}
}






